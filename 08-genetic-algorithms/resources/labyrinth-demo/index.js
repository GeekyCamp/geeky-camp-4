let canvas, context;
let agentSpeedInput;
let population;
let shorttestpath = 0;

let SIMULATION_STEPS = 200;
const FINISH_X = 45;
const FINISH_Y = 45;
const LABIRINTH_SIZE = 50;

let DRAW_SIMULATION = true;
let DRAW_GRAPHS = false;

window.onload = () => {
  loadUI();

  agentSpeedInput = document.getElementById('agentSpeed');
  canvas = document.getElementById('area');
  context = canvas.getContext('2d');
  initLabirinth((labirinth) => {
    console.log(labirinth);
    shorttestpath = bfs(0, 0, labirinth);
    console.log(shorttestpath);
    population = generateRandomPopulation(100, SIMULATION_STEPS);
    if (DRAW_SIMULATION) {
      let agents = initializeSimulationAgents(population);
      updateStep(context, agents, labirinth, SIMULATION_STEPS);
    } else {
      update(context, labirinth, SIMULATION_STEPS);
    }
  });
}

const updateStep = function (context, agents, labirinth, simulationStep) {
  if (!DRAW_SIMULATION) {
    drawLabirinth(context, labirinth);
    setTimeout(update.bind(null, context, labirinth, SIMULATION_STEPS), 1);
    return;
  }

  simulateAgents(agents, labirinth);
  drawLabirinth(context, labirinth);
  drawAgents(context, agents);

  if (simulationStep > 0) {
    setTimeout(updateStep.bind(null, context, agents, labirinth, simulationStep - 1));
  } else {
    commonUpdate(agents, labirinth);

    agents = initializeSimulationAgents(population);
    setTimeout(updateStep.bind(null, context, agents, labirinth, SIMULATION_STEPS), 1);
  }
}

const update = (context, labirinth, simulationSteps) => {
  let agents = initializeSimulationAgents(population);
  if (DRAW_SIMULATION) {
    setTimeout(updateStep.bind(null, context, agents, labirinth, SIMULATION_STEPS), 1);
    return;
  }

  for (let i = 0; i < simulationSteps; i++) {
    simulateAgents(agents, labirinth);
  }

  commonUpdate(agents, labirinth);

  setTimeout(update.bind(null, context, labirinth, SIMULATION_STEPS), 1);
}

const commonUpdate = (agents, labirinth) => {
  evaluateAgentsBFS(agents, labirinth);
  agents.forEach((agent, index) => {
    population.individuals[index].fitness = agent.score;
  });
  statistics.generationNumber++;
  statistics.averageFitness = population.individuals.reduce((prev, current) => prev + current.fitness, 0) / population.individuals.length;
  population = population.nextGeneration();
  lifetimeStatistics.fittests.push(population.individuals[0].fitness);
  lifetimeStatistics.numberOfFinished.push(statistics.numberOfFinished);
  statistics.bestFitness = population.individuals[0].fitness;
  statistics.bestNumberOfSteps = agents.sort((a, b) => a.stepIndex - b.stepIndex)[0].stepIndex;
  statistics.numberOfFinished = agents.filter(a => a.freeze).length;
  updateStatistics();

  if (DRAW_GRAPHS) {
    drawLines('chart-best', lifetimeStatistics.fittests);
    drawLines('chart-finished', lifetimeStatistics.numberOfFinished);
  }
}

const evaluateAgentsManhatan = (agents) => {
  agents.forEach(agent => {
    agent.score = Math.abs(FINISH_X - agent.x) + Math.abs(FINISH_Y - agent.y) + agent.stepIndex;
  });
}

const evaluateAgentsBFS = (agents, labirinth) => {
  agents.forEach(agent => {
    let pathLeft = bfs(agent.x, agent.y, labirinth);
    agent.score = pathLeft + agent.stepIndex;
  });
}

const initializeSimulationAgents = (population) => {
  let agents = [];
  for (let i = 0; i < population.size; i++) {
    population.individuals[i];
    agents.push({
      x: 0,
      y: 0,
      steps: population.individuals[i].chromozomes,
      stepIndex: 0,
      score: 0,
      freeze: false
    });
  }

  return agents;
}

const simulateAgents = (agents, labirinth) => {
  agents.forEach(agent => {
    if (agent.freeze) { return; }
    if (labirinth[agent.x][agent.y] === 'F') {
      agent.freeze = true;
      return;
    }
    switch (agent.steps[agent.stepIndex]) {
      case 0:
        if (agent.x + 1 < LABIRINTH_SIZE && labirinth[agent.x + 1][agent.y] !== 0) {
          agent.x += 1;
        }
        break;
      case 1:
        if (agent.y + 1 < LABIRINTH_SIZE && labirinth[agent.x][agent.y + 1] !== 0) {
          agent.y += 1;
        }
        break;
      case 2:
        if (agent.x - 1 >= 0 && labirinth[agent.x - 1][agent.y] !== 0) {
          agent.x -= 1;
        }
        break;
      case 3:
        if (agent.y - 1 >= 0 && labirinth[agent.x][agent.y - 1] !== 0) {
          agent.y -= 1;
        }
        break;
    }
    agent.stepIndex++;
  });
}

const drawAgents = (ctx, agents) => {
  agents.forEach(agent => {
    ctx.beginPath();
    ctx.rect(agent.x * 32, agent.y * 32, 32, 32);
    ctx.fillStyle = 'yellow';
    ctx.fill();
  });
}

const drawLabirinth = (ctx, labirinth) => {
  ctx.clearRect(0, 0, 2000, 2000);
  for (let i = 0; i < LABIRINTH_SIZE; i++) {
    for (let j = 0; j < LABIRINTH_SIZE; j++) {
      if (labirinth[i][j] !== 0) {
        ctx.beginPath();
        ctx.rect(i * 32, j * 32, 32, 32);
        if (labirinth[i][j] === 'S') {
          ctx.fillStyle = 'red';
        } else if (labirinth[i][j] === 'F') {
          ctx.fillStyle = 'green';
        } else {
          ctx.fillStyle = 'black';
        }
        ctx.fill();
        ctx.strokeStyle = 'white';
        ctx.stroke();
      }
    }
  }
}

const initLabirinth = (cb) => {
  let labirinth = [];
  let visited = [];
  for (let i = 0; i < LABIRINTH_SIZE; i++) {
    labirinth[i] = [];
    visited[i] = [];
    for (let j = 0; j < LABIRINTH_SIZE; j++) {
      labirinth[i][j] = 0;
      visited[i][j] = 0;
    }
  }

  const drawInterval = setInterval(drawLabirinth.bind(undefined, context, labirinth), 10);

  labirinth[0][0] = 'S';
  labirinth[FINISH_X][FINISH_Y] = 'F';
  build(labirinth, visited, labirinth => {
    clearInterval(drawInterval);
    cb(labirinth);
  });
}


const build = (labirinth, visited, cb) => {
  let stack = [];
  let current = { x: 0, y: 0 };
  visited[0][0] = true;

  buildStep(labirinth, visited, stack, current, cb);
}

const buildStep = (labirinth, visited, stack, current, cb) => {
  let neighbours = getNeighbours(current, visited);
  if (stack.length > 0 || neighbours.length > 0) {
    if (neighbours.length > 0) {
      stack.push(current);
      let chosen = neighbours[Math.floor(Math.random() * neighbours.length)];
      if (labirinth[chosen.x][chosen.y] === 0) {
        labirinth[chosen.x][chosen.y] = 1;
      }
      current = chosen;
      visited[current.x][current.y] = true;
    } else {
      current = stack.pop();
    }

    setTimeout(buildStep.bind(this, labirinth, visited, stack, current, cb), 1);
    // buildStep(labirinth, visited, stack, current, cb);
  } else {
    cb(labirinth);
  }
}

const getNeighbours = (current, visited) => {
  let neighbours = [];
  if (checkLeft(current, visited)) {
    neighbours.push({ x: current.x - 1, y: current.y });
  }
  if (checkRight(current, visited)) {
    neighbours.push({ x: current.x + 1, y: current.y });
  }
  if (checkUp(current, visited)) {
    neighbours.push({ x: current.x, y: current.y - 1 });
  }
  if (checkDown(current, visited)) {
    neighbours.push({ x: current.x, y: current.y + 1 });
  }
  return neighbours;
}

const checkDown = (current, visited) => {
  return (current.y + 1 < LABIRINTH_SIZE &&
    !isVisited(current.x, current.y + 1, visited) &&
    !isVisited(current.x - 1, current.y + 1, visited) &&
    !isVisited(current.x + 1, current.y + 1, visited));
}

const checkUp = (current, visited) => {
  return (current.y - 1 >= 0 &&
    !isVisited(current.x, current.y - 1, visited) &&
    !isVisited(current.x - 1, current.y - 1, visited) &&
    !isVisited(current.x + 1, current.y - 1, visited));
}

const checkLeft = (current, visited) => {
  return (current.x - 1 >= 0 &&
    !isVisited(current.x - 1, current.y, visited) &&
    !isVisited(current.x - 1, current.y + 1, visited) &&
    !isVisited(current.x - 1, current.y - 1, visited));
}

const checkRight = (current, visited) => {
  return (current.x + 1 < LABIRINTH_SIZE &&
    !isVisited(current.x + 1, current.y, visited) &&
    !isVisited(current.x + 1, current.y - 1, visited) &&
    !isVisited(current.x + 1, current.y + 1, visited));
}

const isVisited = (x, y, visited) => {
  if (x >= 0 && x < LABIRINTH_SIZE && y >= 0 && y < LABIRINTH_SIZE) {
    return visited[x][y] !== 0;
  }
  return false;
}

const bfs = (x, y, labirinth) => {
  let used = [];
  let parents = [];
  for (let i = 0; i < LABIRINTH_SIZE; i++) {
    used[i] = [];
    parents[i] = [];
    for (let j = 0; j < LABIRINTH_SIZE; j++) {
      used[i][j] = 0;
      parents[i][j] = -1;
    }
  }
  let queue = []
  queue.push({ x, y, level: 0 });
  while (queue.length > 0) {
    let current = queue.shift();
    used[current.x][current.y] = current.level;
    let neighbours = getNeighbours(current, used);
    neighbours.forEach(neighbour => {
      neighbour.level = current.level + 1;
      parents[neighbour.x][neighbour.y] = current;
    });
    queue = queue.concat(neighbours);
  }

  return used[FINISH_X][FINISH_Y];
}

const drawLines = (container, lines) => {
  let gen = 1;
  lines = lines.map((val, index) => {
    return {y: val, x: gen++};
  });
  var chart = new CanvasJS.Chart(container, {
    zoomEnabled: true,
    data: [{
      type: 'line',
      dataPoints: lines
    }]
  });
  chart.render();
}