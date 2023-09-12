import { Injectable, inject } from '@angular/core';
import { Joke } from '../interfaces/joke';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FetcherService {

  http = inject(HttpClient)
  constructor() { }

  async getJoke () : Promise<Joke> {
    const jokeResponse = await fetch('https://jokes.salt.dev/api/jokes/random');
    const joke = await jokeResponse.json();
    return joke;
  }

  getJokeObs () {
    return this.http.get<Joke>('https://jokes.salt.dev/api/jokes/random');
  }

}
