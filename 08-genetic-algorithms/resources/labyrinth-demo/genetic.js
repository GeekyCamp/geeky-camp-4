let Population = function (size, selector, crossover, mutate) {
  this.size = size;
  this.individuals = [];
  this.selector = selector;
  this.crossover = crossover;
  this.mutate = mutate;
}



Population.prototype.nextGeneration = function () {
  this.individuals.sort((a, b) => a.fitness - b.fitness);
  let newIndividuals = [];
  newIndividuals.push(this.individuals[0]);
  newIndividuals.push(this.individuals[1]);
  newIndividuals.push(this.individuals[2]);
  newIndividuals.push(this.individuals[3]);
  newIndividuals.push(this.individuals[4]);
  while (newIndividuals.length < this.size) {
    const parent1 = this.selector(this.individuals, 5);
    const parent2 = this.selector(this.individuals, 5);
    newIndividuals.push(this.mutate(this.crossover(parent1, parent2), 0.7));
  }

  this.individuals = newIndividuals;
  return this;
}

let DNA = function (numberOfChromozomes) {
  this.chromozomes = [];
  this.fitness = Infinity;
}

const generateRandomDNA = numberOfChromozomes => {
  let dna = new DNA(numberOfChromozomes);
  for (let i = 0; i < numberOfChromozomes; i++) {
    dna.chromozomes.push(Math.floor(Math.random() * 4));
  }
  return dna;
}

const generateRandomPopulation = (size, chromozomes) => {
  let population = new Population(size, tournamentSelector, randomCrossover, mutatePercent);
  for (let i = 0; i < size; i++) {
    population.individuals.push(generateRandomDNA(chromozomes));
  }
  return population;
}

const tournamentSelector = (individuals, size) => {
  let chosen = []
  for (let i = 0; i < size; i++) {
    chosen.push(individuals[Math.floor(Math.random() * individuals.length)]);
  }
  return chosen.sort((a, b) => a.fitness - b.fitness)[0];
}

const randomCrossover = (dna1, dna2) => {
  let child = new DNA(dna1.chromozomes.length);
  for (let i = 0; i < dna1.chromozomes.length; i++) {
    if (Math.random() < 0.5) {
      child.chromozomes.push(dna1.chromozomes[i]);
    } else {
      child.chromozomes.push(dna2.chromozomes[i]);
    }
  }
  return child;
}

const twoPointCrossover = (dna1, dna2) => {
  let child = new DNA(dna1.chromozomes.length);
  let point1 = Math.max(Math.floor(Math.random() * dna1.chromozomes.length) - 10, 0);
  let point2 = Math.min(point1 + Math.floor(Math.random() *  dna1.chromozomes.length), dna1.chromozomes.length - 3);

  for (let i = 0; i < point1; i++) {
    child.chromozomes.push(dna1.chromozomes[i]);
  }
  for (let i = point1; i < point2; i++) {
    child.chromozomes.push(dna2.chromozomes[i]);
  }
  for (let i = point2; i < dna1.chromozomes.length; i++) {
    child.chromozomes.push(dna1.chromozomes[i]);
  }
  return child;
}

const mutatePercent = (dna, mutateRate) => {
  if (Math.random() < mutateRate) {
    let swaps = Math.floor(40/100 * dna.chromozomes.length);
    swaps = Math.floor(Math.random() * swaps) + 1;
    for (let i = 0; i < swaps; i++) {
      dna.chromozomes[Math.floor(Math.random() * dna.chromozomes.length)] = dna.chromozomes[Math.floor(Math.random() * dna.chromozomes.length)];
    }
  }

  return dna;
}

const simpleMutate = (dna, mutateRate) => {
  if (Math.random() < mutateRate) {
    dna.chromozomes[Math.floor(Math.random() * dna.chromozomes.length)] = dna.chromozomes[Math.floor(Math.random() * dna.chromozomes.length)];
  }

  return dna;
}

const improveChromozome = (dna) => {

}