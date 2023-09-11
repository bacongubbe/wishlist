import { Injectable } from '@angular/core';
import { Joke } from '../interfaces/joke';

@Injectable({
  providedIn: 'root'
})
export class FetcherService {

  constructor() { }

  async getJoke () : Promise<Joke> {
    const jokeResponse = await fetch('https://jokes.salt.dev/api/jokes/random');
    const joke = await jokeResponse.json();
    return joke;
  }

}
