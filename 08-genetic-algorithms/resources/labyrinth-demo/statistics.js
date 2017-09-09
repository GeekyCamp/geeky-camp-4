let statistics = {
  generationNumber: 0,
  averageFitness: 0,
  bestFitness: 0,
  bestNumberOfSteps: 0,
  numberOfFinished: 0
};

let lifetimeStatistics = {
  fittests: [],
  numberOfFinished: []
};

const updateStatistics = () => {
  const generationNumber = document.getElementById('generation-number');
  const averageFitness = document.getElementById('average-fitness');
  const bestFitness = document.getElementById('best-fitness');
  const bestSteps = document.getElementById('best-steps');
  const finishedNumber = document.getElementById('finished-number');
  const crossoverName = document.getElementById('crossover-name');

  generationNumber.innerHTML = statistics.generationNumber;
  averageFitness.innerHTML = statistics.averageFitness;
  bestFitness.innerHTML = statistics.bestFitness;
  bestSteps.innerHTML = statistics.bestNumberOfSteps;
  finishedNumber.innerHTML = statistics.numberOfFinished;
  crossoverName.innerHTML = population.crossover.name;
}