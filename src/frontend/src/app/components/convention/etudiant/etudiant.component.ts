import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { AuthService } from "../../../services/auth.service";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MessageService } from "../../../services/message.service";
import { EtudiantService } from "../../../services/etudiant.service";
import { MatExpansionPanel } from "@angular/material/expansion";
import { LdapService } from "../../../services/ldap.service";
import { TypeConventionService } from "../../../services/type-convention.service";
import { LangueConventionService } from "../../../services/langue-convention.service";
import * as _ from "lodash";
import { CentreGestionService } from "../../../services/centre-gestion.service";
import { ConventionService } from "../../../services/convention.service";

@Component({
  selector: 'app-convention-etudiant',
  templateUrl: './etudiant.component.html',
  styleUrls: ['./etudiant.component.scss']
})
export class EtudiantComponent implements OnInit, OnChanges {

  isEtudiant: boolean = true;

  form: FormGroup;
  columns = ['numetudiant', 'nomprenom', 'action'];
  etudiants: any[] = [];
  etudiant: any;
  selectedRow: any;
  selectedNumEtudiant: string|null = null;
  inscriptions: any[] = [];
  centreGestion: any;

  formConvention: FormGroup;

  typeConventions: any[] = [];
  langueConventions: any[] = [];

  @Input() convention: any;
  @Output() validated = new EventEmitter<any>();

  @ViewChild(MatExpansionPanel) searchEtudiantPanel: MatExpansionPanel|undefined;

  constructor(
    private authService: AuthService,
    private etudiantService: EtudiantService,
    private fb: FormBuilder,
    private messageService: MessageService,
    private ldapService: LdapService,
    private typeConventionService: TypeConventionService,
    private langueConventionService: LangueConventionService,
    private centreGestionService: CentreGestionService,
    private conventionService: ConventionService,
  ) {
    this.form = this.fb.group({
      id: [null, []],
      nom: [null, []],
      prenom: [null, []],
    });
  }

  ngOnInit(): void {
    this.isEtudiant = this.authService.isEtudiant();
    let codEtu: string|undefined = undefined;
    if (this.convention && this.convention.etudiant) {
      codEtu = this.convention.etudiant.numEtudiant;
    } else {
      if (this.isEtudiant) {
        this.etudiantService.getByLogin(this.authService.userConnected.login).subscribe((response: any) => {
          codEtu = response.numEtudiant;
        });
      }
    }

    this.formConvention = this.fb.group({
      adresseEtudiant: [this.convention.adresseEtudiant, [Validators.required]],
      codePostalEtudiant: [this.convention.codePostalEtudiant, [Validators.required]],
      villeEtudiant: [this.convention.villeEtudiant, [Validators.required]],
      paysEtudiant: [this.convention.paysEtudiant, [Validators.required]],
      telEtudiant: [this.convention.telEtudiant, []],
      telPortableEtudiant: [this.convention.telPortableEtudiant, []],
      courrielPersoEtudiant: [this.convention.courrielPersoEtudiant, [Validators.required, Validators.email]],
      inscription: [null, [Validators.required]],
      idTypeConvention: [this.convention.typeConvention ? this.convention.typeConvention.id : null, [Validators.required]],
      codeLangueConvention: [this.convention.langueConvention ? this.convention.langueConvention.code : null, [Validators.required]],
    });

    if (codEtu !== undefined) {
      this.choose({codEtu: codEtu});
    }
    this.typeConventionService.getListActive().subscribe((response: any) => {
      this.typeConventions = response.data;
    });
    this.langueConventionService.getListActive().subscribe((response: any) => {
      this.langueConventions = response.data;
    });

    this.formConvention.get('inscription')?.valueChanges.subscribe((inscription: any) => {
      if (inscription) {
        this.centreGestionService.findByEtape(inscription.codeEtp, inscription.codVrsVet).subscribe((response: any) => {
          this.centreGestion = response;
        });
      }
    });
  }

  ngOnChanges(changes: SimpleChanges) {
  }

  search(): void {
    this.selectedRow = undefined;
    if (!this.form.get('id')?.value && !this.form.get('nom')?.value && !this.form.get('prenom')?.value) {
      this.messageService.setError(`Veuillez renseigner au moins l'un des critères`);
      return;
    }
    this.etudiant = undefined;
    this.selectedNumEtudiant = null;
    this.ldapService.searchEtudiants(this.form.value).subscribe((response: any) => {
      this.etudiants = response;
      if (this.etudiants.length === 1) {
        this.choose(this.etudiants[0]);
      }
    });
  }

  choose(row: any): void {
    this.selectedRow = row;
    this.etudiantService.getApogeeData(row.codEtu).subscribe((response: any) => {
      this.selectedNumEtudiant = row.codEtu;
      if (this.searchEtudiantPanel) {
        this.searchEtudiantPanel.expanded = false;
      }
      this.etudiant = response;
      this.formConvention.get('adresseEtudiant')?.setValue(this.etudiant.mainAddress);
      this.formConvention.get('codePostalEtudiant')?.setValue(this.etudiant.postalCode);
      this.formConvention.get('villeEtudiant')?.setValue(this.etudiant.town);
      this.formConvention.get('paysEtudiant')?.setValue(this.etudiant.country);
      this.formConvention.get('telEtudiant')?.setValue(this.etudiant.phone);
      this.formConvention.get('telPortableEtudiant')?.setValue(this.etudiant.portablePhone);
      this.formConvention.get('courrielPersoEtudiant')?.setValue(this.etudiant.mailPerso);
    });
    this.etudiantService.getApogeeInscriptions(row.codEtu).subscribe((response: any) => {
      this.inscriptions = response;
      if (this.inscriptions.length === 1) {
        this.formConvention.get('inscription')?.setValue(this.inscriptions[0]);
      }
      if (this.convention.etape) {
        const inscription = this.inscriptions.find((i: any) => {
          return i.codeEtp === this.convention.etape.id.code;
        });
        if (inscription) {
          this.formConvention.get('inscription')?.setValue(inscription);
        }
      }
    });
  }

  get selectedInscription() {
    return this.formConvention.controls.inscription.value;
  }

  isSelected(row: any): boolean {
    return _.isEqual(row, this.selectedRow);
  }

  validate(): void {
    if (this.formConvention.valid) {
      const data = {...this.formConvention.value};
      delete data.isncription;
      data.numEtudiant = this.selectedNumEtudiant;
      data.codeComposante = this.formConvention.value.inscription.codeComposante;
      data.codeEtape = this.formConvention.value.inscription.codeEtp;
      data.codeVerionEtape = this.formConvention.value.inscription.codVrsVet
      data.annee = this.formConvention.value.inscription.annee
      if (this.isEtudiant) {
        data.etudiantLogin = this.authService.userConnected.login;
      } else if (this.selectedRow && this.selectedRow.supannAliasLogin) {
        data.etudiantLogin = this.selectedRow.supannAliasLogin
      } else if (this.convention && this.convention.etudiant) {
        data.etudiantLogin = this.convention.etudiant.identEtudiant;
      }
      if (!this.convention || !this.convention.id) {
        this.conventionService.create(data).subscribe((response: any) => {
          this.validated.emit(response);
        });
      } else {
        this.conventionService.update(this.convention.id, data).subscribe((response: any) => {
          this.validated.emit(response);
        });
      }
    }
  }

}
