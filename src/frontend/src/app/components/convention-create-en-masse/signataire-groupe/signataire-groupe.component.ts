import { Component, EventEmitter, Input, Output, OnChanges, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { TableComponent } from "../../table/table.component";
import { GroupeEtudiantService } from "../../../services/groupe-etudiant.service";
import { ConventionService } from "../../../services/convention.service";
import { AuthService } from "../../../services/auth.service";
import { Router } from "@angular/router";
import { EtudiantGroupeEtudiantService } from "../../../services/etudiant-groupe-etudiant.service";
import { MessageService } from "../../../services/message.service";
import { ConfigService } from "../../../services/config.service";
import { SortDirection } from "@angular/material/sort";
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { SignataireGroupeModalComponent } from './signataire-groupe-modal/signataire-groupe-modal.component';

@Component({
  selector: 'app-signataire-groupe',
  templateUrl: './signataire-groupe.component.html',
  styleUrls: ['./signataire-groupe.component.scss']
})
export class SignataireGroupeComponent implements OnInit {

  columns: string[] = [];
  sortColumn = 'prenom';
  sortDirection: SortDirection = 'desc';
  filters: any[] = [];
  selected: any[] = [];

  structures: any[] = [];
  services: any[] = [];

  @Input() sharedData: any;
  @Input() groupeEtudiant: any;
  @Output() validated = new EventEmitter<any>();

  @ViewChild(TableComponent) appTable: TableComponent | undefined;

  constructor(
    public groupeEtudiantService: GroupeEtudiantService,
    public etudiantGroupeEtudiantService: EtudiantGroupeEtudiantService,
    private conventionService: ConventionService,
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService,
    private configService: ConfigService,
    public matDialog: MatDialog,
  ) {
  }

  ngOnInit(): void {
    this.columns = [...this.sharedData.columns];
    this.columns.push('etab');
    this.columns.push('service')
    this.columns.push('signataire')
    this.filters = [...this.sharedData.filters];
    this.filters.push({ id: 'convention.structure.id', libelle: 'Structure d\'accueil', type: 'list', options: [], keyLibelle: 'raisonSociale', keyId: 'id'});
    this.filters.push({ id: 'convention.service.id', libelle: 'Service d\'accueil', type: 'list', options: [], keyLibelle: 'nom', keyId: 'id'});
  }

  ngOnChanges(): void{
    this.initStructureFilter();
    this.initServiceFilters();
    this.appTable?.update();
    this.selected = [];
  }

  ngAfterViewInit(): void {
      this.appTable?.setFilterValue('groupeEtudiant.id', this.groupeEtudiant.id);
      this.appTable?.setFilterOption('ufr.id', this.sharedData.ufrList);
      this.appTable?.setFilterOption('etape.id', this.sharedData.etapeList);
      this.appTable?.setFilterOption('convention.annee', this.sharedData.annees);
      this.initStructureFilter();
      this.initServiceFilters();
      this.appTable?.update();
      this.selected = [];
  }

  initStructureFilter(): void {
    if(this.groupeEtudiant && this.groupeEtudiant.convention.structure && this.groupeEtudiant.convention.service){
      this.structures = this.groupeEtudiant.etudiantGroupeEtudiants.map((e: any) => e.convention.structure??this.groupeEtudiant.convention.structure);
      this.structures = [...new Map(this.structures.map(e => [e.id, {id:e.id,raisonSociale:e.raisonSociale}])).values()]
      this.appTable?.setFilterOption('convention.structure.id', this.structures);
    }
  }

  initServiceFilters(): void {
    if(this.groupeEtudiant && this.groupeEtudiant.convention.structure && this.groupeEtudiant.convention.service){
      this.services = this.groupeEtudiant.etudiantGroupeEtudiants.map((e: any) => e.convention.service??this.groupeEtudiant.convention.service);
      this.services = [...new Map(this.services.map(e => [e.id, {id:e.id,nom:e.nom}])).values()]
      this.appTable?.setFilterOption('convention.service.id', this.services);
    }
  }

  isSelected(data: any): boolean {
    return this.selected.find((r: any) => {return r.id === data.id}) !== undefined;
  }

  toggleSelected(data: any): void {
    const index = this.selected.findIndex((r: any) => {return r.id === data.id});
    if (index > -1) {
      this.selected.splice(index, 1);
    } else {
      this.selected.push(data);
    }
  }

  masterToggle(): void {
    if (this.isAllSelected()) {
      this.selected = [];
      return;
    }
    this.appTable?.data.forEach((d: any) => {
      const index = this.selected.findIndex((s: any) => s.id === d.id);
      if (index === -1) {
        this.selected.push(d);
      }
    });
  }

  isAllSelected(): boolean {
    let allSelected = true;
    if(this.appTable?.data){
      this.appTable?.data.forEach((data: any) => {
        const index = this.selected.findIndex((r: any) => {return r.id === data.id});
        if (index === -1) {
           allSelected = false;
        }
      });
    }
    return allSelected;
  }

  selectForGroup(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '1200px';
    dialogConfig.height = '1000px';
    dialogConfig.data = {convention: this.groupeEtudiant.convention};
    const modalDialog = this.matDialog.open(SignataireGroupeModalComponent, dialogConfig);
    modalDialog.afterClosed().subscribe(dialogResponse => {
      if (dialogResponse) {
        this.updateSignataire(this.groupeEtudiant.convention.id,dialogResponse)
      }
    });
  }

  selectForSelected(): void {
    this.structures = this.selected.map((e: any) => e.convention.structure??this.groupeEtudiant.convention.structure);
    this.structures = [...new Map(this.structures.map(e => [e.id, {id:e.id,raisonSociale:e.raisonSociale}])).values()]
    if(this.structures.length == 1){
      const dialogConfig = new MatDialogConfig();
      dialogConfig.width = '1200px';
      dialogConfig.height = '1000px';
      let convention = this.selected[0].convention;
      dialogConfig.data = {convention: convention};
      const modalDialog = this.matDialog.open(SignataireGroupeModalComponent, dialogConfig);
      modalDialog.afterClosed().subscribe(dialogResponse => {
        if (dialogResponse) {
          for(const etu of this.selected){
            this.updateSignataire(etu.convention.id,dialogResponse);
          }
        }
      });
    }else{
        this.messageService.setError('Il faut selectionner des étudiants ayant la même structure');
    }
  }

  updateSignataire(conventionId: number, signataireId: number): void {
    const data = {
      "field":'idSignataire',
      "value":signataireId,
    };
    this.conventionService.patch(conventionId, data).subscribe((response: any) => {
        this.messageService.setSuccess('Signataire affecté avec succès');
        this.groupeEtudiantService.getById(this.groupeEtudiant.id).subscribe((response: any) => {
          this.validated.emit(response);
        });
    });
  }
}
