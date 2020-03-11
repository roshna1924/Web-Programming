import { Injectable } from '@angular/core';

@Injectable()
export class BestScoreManager {

  private SnakeGame = 'Snake Game';

  public store(score: number) {
    localStorage.setItem(this.SnakeGame, JSON.stringify({ 'best_score': score }));
  }

  public retrieve() {
    let storage = this.parse();
    if (!storage) {
      this.store(0);
      storage = this.parse();
    }

    return storage.best_score;
  }

  private parse() {
    return JSON.parse(localStorage.getItem(this.SnakeGame));
  }
}
