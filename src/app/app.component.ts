import { Component } from '@angular/core';
import { WeatherComponent } from "./weather/weather.component";

@Component({
  selector: 'app-root',
  imports: [WeatherComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'weather-ui';
}
