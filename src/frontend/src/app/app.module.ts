import { APP_INITIALIZER, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from "./app-routing.module";
import { HeaderComponent } from './components/header/header.component';
import { MatSidenavModule } from "@angular/material/sidenav";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { MenuComponent } from './components/menu/menu.component';
import { MatListModule } from "@angular/material/list";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { CookieService } from "ngx-cookie-service";
import { TechnicalInterceptor } from "./interceptors/technical.interceptor";
import { HomeComponent } from './components/home/home.component';
import { TitleComponent } from './components/title/title.component';
import { MessageComponent } from './components/message/message.component';
import { MatDialogModule } from "@angular/material/dialog";
import { MatTabsModule } from "@angular/material/tabs";
import {
  MAT_COLOR_FORMATS,
  NGX_MAT_COLOR_FORMATS,
  NgxMatColorPickerModule
} from "@angular-material-components/color-picker";
import { MatFormFieldModule } from "@angular/material/form-field";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatInputModule } from "@angular/material/input";
import { AdminUserComponent } from './components/admin/admin-user/admin-user.component';
import { MatTableModule } from "@angular/material/table";
import { MatSortModule } from "@angular/material/sort";
import { MatTooltipModule } from "@angular/material/tooltip";
import { MatPaginatorIntl, MatPaginatorModule } from "@angular/material/paginator";

import { registerLocaleData } from "@angular/common";
import localeFr from '@angular/common/locales/fr';
import { MAT_DATE_LOCALE } from "@angular/material/core";
import { PaginatorIntl } from "./paginator-intl";
import { TableComponent } from './components/table/table.component';
import { BooleanPipe } from './pipes/boolean.pipe';
import { MatCardModule } from "@angular/material/card";
import { RoleLibellePipe } from './pipes/role-libelle.pipe';
import { MatSelectModule } from "@angular/material/select";
import { MatCheckboxModule } from "@angular/material/checkbox";
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ConventionComponent } from './components/convention/convention.component';
import { MatStepperModule } from "@angular/material/stepper";
import { AdminNomenclaturesComponent } from './components/admin/admin-nomenclatures/admin-nomenclatures.component';
import { AdminNomenclaturesEditionComponent } from './components/admin/admin-nomenclatures/admin-nomenclatures-edition/admin-nomenclatures-edition.component';
import { AdminRoleComponent } from './components/admin/admin-role/admin-role.component';
import { ConfirmComponent } from './components/confirm/confirm.component';
import { ConfigGeneraleComponent } from './components/admin/config-generale/config-generale.component';
import { MatRadioModule } from "@angular/material/radio";
import { ContenuComponent } from './components/admin/contenu/contenu.component';
import { ContenuPipe } from './pipes/contenu.pipe';
import { ContenuService } from "./services/contenu.service";
import { CentreGestionSearchComponent } from './components/centre-gestion-search/centre-gestion-search.component';
import { EtabAccueilComponent } from './components/convention/etab-accueil/etab-accueil.component';
import { MatExpansionModule } from "@angular/material/expansion";
import { CentreGestionComponent } from './components/centre-gestion/centre-gestion.component';
import { EtudiantComponent } from './components/convention/etudiant/etudiant.component';
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { LoaderComponent } from './components/loader/loader.component';
import { AdminNomenclaturesCreationComponent } from './components/admin/admin-nomenclatures/admin-nomenclatures-creation/admin-nomenclatures-creation.component';
import { QuillModule } from "ngx-quill";
import { FormErrorComponent } from './components/form-error/form-error.component';
import { MatProgressBarModule } from "@angular/material/progress-bar";
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { ServiceAccueilComponent } from './components/convention/service-accueil/service-accueil.component';
import { TuteurProComponent } from './components/convention/tuteur-pro/tuteur-pro.component';
import { CoordCentreComponent } from './components/centre-gestion/coord-centre/coord-centre.component';
import { SignataireComponent } from './components/convention/signataire/signataire.component';
import { EnseignantReferentComponent } from './components/convention/enseignant-referent/enseignant-referent.component';
import { TemplateMailComponent } from './components/admin/template-mail/template-mail.component';
import { ParamCentreComponent } from './components/centre-gestion/param-centre/param-centre.component';
import { MailTesterComponent } from "./components/admin/template-mail/mail-tester/mail-tester.component";
import { CreateDialogComponent } from './components/admin/admin-user/create-dialog/create-dialog.component';
import { GestionEtabAccueilComponent } from "./components/gestion-etab-accueil/gestion-etab-accueil.component";
import { EtabAccueilFormComponent } from './components/gestion-etab-accueil/etab-accueil-form/etab-accueil-form.component';
import { StageComponent } from './components/convention/stage/stage.component';
import { ServiceAccueilFormComponent } from './components/gestion-etab-accueil/service-accueil-form/service-accueil-form.component';
import { ContactFormComponent } from './components/gestion-etab-accueil/contact-form/contact-form.component';
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search';
import { GestionnairesComponent } from './components/centre-gestion/gestionnaires/gestionnaires.component';
import { RecapitulatifComponent } from './components/convention/recapitulatif/recapitulatif.component';
import { AvenantComponent } from './components/convention/avenant/avenant.component';
import { MatChipsModule } from "@angular/material/chips";
import { MatAutocompleteModule } from "@angular/material/autocomplete";
import { AvenantFormComponent } from './components/convention/avenant/avenant-form/avenant-form.component';
import { MatMenuModule } from "@angular/material/menu";
import { ValidationComponent } from './components/convention/validation/validation.component';
import { ValidationCardComponent } from './components/convention/validation/validation-card/validation-card.component';
import { LogoCentreComponent } from './components/centre-gestion/logo-centre/logo-centre.component';
import { DragDropModule } from "@angular/cdk/drag-drop";
import { AvenantViewComponent } from './components/convention/avenant/avenant-view/avenant-view.component';
import { CalendrierComponent } from './components/convention/stage/calendrier/calendrier.component';
import { CKEditorModule } from "@ckeditor/ckeditor5-angular";
import { TemplateConventionComponent } from './components/admin/template-convention/template-convention.component';

registerLocaleData(localeFr, 'fr');

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MenuComponent,
    HomeComponent,
    TitleComponent,
    MessageComponent,
    AdminUserComponent,
    TableComponent,
    BooleanPipe,
    RoleLibellePipe,
    DashboardComponent,
    ConventionComponent,
    AdminNomenclaturesComponent,
    AdminNomenclaturesEditionComponent,
    AdminRoleComponent,
    ConfirmComponent,
    ConfigGeneraleComponent,
    ContenuComponent,
    ContenuPipe,
    CentreGestionSearchComponent,
    EtabAccueilComponent,
    CentreGestionComponent,
    EtudiantComponent,
    LoaderComponent,
    AdminNomenclaturesCreationComponent,
    FormErrorComponent,
    ServiceAccueilComponent,
    CoordCentreComponent,
    TuteurProComponent,
    SignataireComponent,
    EnseignantReferentComponent,
    TemplateMailComponent,
    ParamCentreComponent,
    MailTesterComponent,
    CreateDialogComponent,
    GestionEtabAccueilComponent,
    EtabAccueilFormComponent,
    StageComponent,
    ServiceAccueilFormComponent,
    ContactFormComponent,
    GestionnairesComponent,
    RecapitulatifComponent,
    AvenantComponent,
    AvenantFormComponent,
    ValidationComponent,
    ValidationCardComponent,
    LogoCentreComponent,
    AvenantViewComponent,
    CalendrierComponent,
    TemplateConventionComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    MatSidenavModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatDialogModule,
    MatTabsModule,
    MatFormFieldModule,
    MatInputModule,
    NgxMatColorPickerModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatTooltipModule,
    MatCardModule,
    MatSelectModule,
    MatCheckboxModule,
    MatStepperModule,
    MatRadioModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    QuillModule.forRoot(),
    MatProgressBarModule,
    MatDatepickerModule,
    MatNativeDateModule,
    NgxMatSelectSearchModule,
    MatChipsModule,
    MatAutocompleteModule,
    MatMenuModule,
    DragDropModule,
    CKEditorModule,
  ],
  providers: [
    CookieService,
    ContenuService,
    { provide: LOCALE_ID, useValue: 'fr' },
    { provide: HTTP_INTERCEPTORS, useClass: TechnicalInterceptor, multi: true },
    { provide: MAT_COLOR_FORMATS, useValue: NGX_MAT_COLOR_FORMATS },
    { provide: MAT_DATE_LOCALE, useValue: 'fr-FR' },
    { provide: MatPaginatorIntl, useClass: PaginatorIntl },
    { provide: APP_INITIALIZER, useFactory: (cs: ContenuService) => () => cs.getAllLibelle(), deps: [ContenuService], multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
