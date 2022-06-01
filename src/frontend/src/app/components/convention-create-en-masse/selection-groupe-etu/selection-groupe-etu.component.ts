import { Component, EventEmitter, Input, Output, OnChanges, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { TableComponent } from "../../table/table.component";
import { GroupeEtudiantService } from "../../../services/groupe-etudiant.service";
import { AuthService } from "../../../services/auth.service";
import { Router } from "@angular/router";
import { StructureService } from "../../../services/structure.service";
import { UfrService } from "../../../services/ufr.service";
import { EtapeService } from "../../../services/etape.service";
import { EtudiantService } from "../../../services/etudiant.service";
import { MessageService } from "../../../services/message.service";
import { ConfigService } from "../../../services/config.service";
import { SortDirection } from "@angular/material/sort";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-selection-groupe-etu',
  templateUrl: './selection-groupe-etu.component.html',
  styleUrls: ['./selection-groupe-etu.component.scss']
})
export class SelectionGroupeEtuComponent implements OnInit {

  columns: string[] = [];
  sortColumn = 'prenom';
  sortDirection: SortDirection = 'desc';
  filters: any[] = [];
  selected: any[] = [];

  anneeEnCours: any|undefined;
  annees: any[] = [];

  form: FormGroup;

  @Input() groupeEtudiant: any;
  @Output() validated = new EventEmitter<any>();

  @ViewChild(TableComponent) appTable: TableComponent | undefined;

  constructor(
    public groupeEtudiantService: GroupeEtudiantService,
    public etudiantService: EtudiantService,
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService,
    private configService: ConfigService,
    private fb: FormBuilder,
  ) {
    this.form = this.fb.group({
      nomGroupe: [null, [Validators.required, Validators.maxLength(100)]],
    });
  }

  ngOnInit(): void {
    this.columns = ['select','numEtudiant','nom', 'prenom', 'mail'];
    this.filters = [
      { id: 'nom', libelle: 'Nom'},
      { id: 'prenom', libelle: 'Prénom'},
      { id: 'numEtudiant', libelle: 'N° étudiant'},
    ];
  }

  ngOnChanges(): void{
    this.form.setValue({
      nomGroupe: this.groupeEtudiant?this.groupeEtudiant.nom:null,
    });
    if(this.groupeEtudiant){
      this.selected = this.groupeEtudiant.etudiantGroupeEtudiants.map((ege: any) => ege.etudiant);
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

  validate(): void {
      if (this.form.valid) {
        const selected = this.selected.map((s: any) => s.id);

        let data = {...this.form.value};
        data.etudiantIds = selected;

        console.log('data : ' + JSON.stringify(data, null, 2))
        if (!this.groupeEtudiant) {
          this.groupeEtudiantService.create(data).subscribe((response: any) => {
            this.validated.emit(response);
          });
        } else {
          this.groupeEtudiantService.update(this.groupeEtudiant.id, data).subscribe((response: any) => {
            this.validated.emit(response);
          });
        }
      }
  }
}