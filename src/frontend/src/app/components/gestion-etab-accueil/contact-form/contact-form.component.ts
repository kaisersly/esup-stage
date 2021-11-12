import { Component, EventEmitter, Input, Output, SimpleChanges, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, Validators } from "@angular/forms";
import { PaysService } from "../../../services/pays.service";
import { ContactService } from "../../../services/contact.service";
import { MessageService } from "../../../services/message.service";

@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.scss']
})
export class ContactFormComponent {

  contact: any;
  service: any;

  civilites: any[] = [];

  form: any;

  constructor(public contactService: ContactService,
              private dialogRef: MatDialogRef<ContactFormComponent>,
              private fb: FormBuilder,
              @Inject(MAT_DIALOG_DATA) data: any
  ) {
    this.contact = data.contact
    this.service = data.service
    this.civilites = data.civilites
    this.form = this.fb.group({
      nom: [null, [Validators.required, Validators.maxLength(50)]],
      prenom: [null, [Validators.required, Validators.maxLength(50)]],
      idCivilite: [null, []],
      fonction: [null, [Validators.required, Validators.maxLength(100)]],
      tel: [null, [Validators.required, Validators.maxLength(50)]],
      mail: [null, [Validators.required, Validators.email, Validators.maxLength(50)]],
      fax: [null, [Validators.maxLength(50)]],
    });

    if (this.contact) {
      this.form.setValue({
        nom: this.contact.nom,
        prenom: this.contact.prenom,
        idCivilite: this.contact.civilite ? this.contact.civilite.id : null,
        fonction: this.contact.fonction,
        tel: this.contact.tel,
        fax: this.contact.fax,
        mail: this.contact.mail,
      });
    }
  }

  close(): void {
    this.dialogRef.close(null);
  }

  save(): void {
    if (this.form.valid) {

      const data = {...this.form.value};

      if (this.contact) {
        this.contactService.update(this.contact.id, data).subscribe((response: any) => {
          this.contact = response;
          this.dialogRef.close(this.contact);
        });
      } else {
        //ajoute un idCentreGestion factice à l'objet contact
        //TODO : récupérer idCentreGestion
        data.idCentreGestion = 1;

        //ajoute idService à l'objet contact
        data.idService = this.service.id;
        this.contactService.create(data).subscribe((response: any) => {
          this.contact = response;
          this.dialogRef.close(this.contact);
        });
      }
    }
  }


}
