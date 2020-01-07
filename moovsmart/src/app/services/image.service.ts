import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({providedIn: 'root'})
export class ImageService {

    constructor(private http: HttpClient) {}

    publicId: string;

    public uploadImage(image: File): Observable<any> {
        const uploadData = new FormData();
        uploadData.append('myPicture', image)
        return this.http.post('http://localhost:8080/api/images', uploadData);
    }
}
