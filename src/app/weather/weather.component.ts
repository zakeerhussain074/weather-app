import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { WeatherService, Forecast } from '../weather.service';

@Component({
  selector: 'app-weather',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.scss']
})
export class WeatherComponent {
  city = '';
  weatherData: Forecast[] = [];

  constructor(private weatherService: WeatherService) {}

  fetchWeather() {
    this.weatherService.getForecast(this.city).subscribe({
      next: data => this.weatherData = data,
      error: err => console.error('Error fetching weather:', err)
    });
  }
}
