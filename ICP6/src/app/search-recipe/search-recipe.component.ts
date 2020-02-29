import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;
  venueList: any;
  recipeList: any;

  currentLat: any;
  currentLong: any;
  geolocationPosition: any;

  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {

    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;
    if (this.recipeValue !== null) {
      this._http.get('https://api.edamam.com/search?q=' + this.recipeValue +
        '&app_id=ea71522a&app_key=b6ea9e74c89a85342c82342a97d7f111').
          subscribe(respReceipe => {
            this.recipeList = respReceipe['hits'];
          }, error => {});

    }

    if (this.placeValue != null && this.placeValue !== '') {
      this._http.get('https://api.foursquare.com/v2/venues/search?client_id=YB3O3HL5NZH2JHOLRTBQPIQVXMB3O42BK4VOL2B1LMWOA1DB' +
        '&client_secret=5R1GS10W5Q1MTPBANPEEO4XGJDPXCZDJ0CLZ1FHBNXQOUFOT&v=20180323&near=' + this.placeValue + '&query=restaurant').
      subscribe(respRestuarant => {
        this.venueList = respRestuarant['response']['venues'];
      }, error => {});
    }
  }
}
