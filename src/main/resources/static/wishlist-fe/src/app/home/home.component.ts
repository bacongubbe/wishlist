import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FetcherService } from '../services/fetcher.service';
import { Joke } from '../interfaces/joke';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})

export class HomeComponent {

  joke : Joke = {text : ''}

  constructor(private fetcher : FetcherService){}

  updateJoke(event : Event) : void {
    event.preventDefault();
    this.fetcher.getJoke()
    .then(res => {
      this.joke = res;
    }).catch(console.error)
  }

}
