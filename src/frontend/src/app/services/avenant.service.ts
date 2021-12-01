import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { PaginatedService } from "./paginated.service";

@Injectable({
  providedIn: 'root'
})
export class AvenantService implements PaginatedService {

  constructor(private http: HttpClient) { }

  getPaginated(page: number, perPage: number, predicate: string, sortOrder: string, filters: string): Observable<any> {
    return this.http.get(environment.apiUrl + "/avenant", {params: {page, perPage, predicate, sortOrder, filters}});
  }

  getByConvention(id: number): Observable<any> {
    return this.http.get(`${environment.apiUrl}/avenant/getByConvention/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(environment.apiUrl + "/avenant", data);
  }

  update(id: number, data: any): Observable<any> {
    return this.http.put(environment.apiUrl + "/avenant/" + id, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(environment.apiUrl + "/avenant/" + id);
  }

  validate(id: number): Observable<any> {
    return this.http.get(`${environment.apiUrl}/avenant/validate/${id}`);
  }

  cancelValidation(id: number): Observable<any> {
    return this.http.get(`${environment.apiUrl}/avenant/validate/cancel/${id}`);
  }
}
