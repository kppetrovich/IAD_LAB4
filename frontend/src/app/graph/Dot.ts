export class Dot {
  constructor(
    public x: number,
    public y: number,
    public r: number,
    public result: boolean
  ) {
  }

  get getColor(): string {
    return this.result ? 'green' : 'red';
  }
}
