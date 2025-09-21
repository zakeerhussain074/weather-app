import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Forecast {
  day: number;
  minTemp: number;
  maxTemp: number;
  windSpeed: number;
  weather: string;
  advice: string;
}

@Injectable({ providedIn: 'root' })
export class WeatherService {
  private apiUrl = 'http://localhost:8080'; // later Docker will replace with ENV/config.json

  constructor(private http: HttpClient) {}

  getForecast(city: string): Observable<Forecast[]> {
    return this.http.get<Forecast[]>(`${this.apiUrl}/weather?city=${encodeURIComponent(city)}`);
  }
}
