const loadUI = () => {
  const buttonSimulation = document.getElementById('toggle-simulation');
  buttonSimulation.addEventListener('click', handleToggleSimulation, false);

  const buttonGraphs = document.getElementById('toggle-graphs');
  buttonGraphs.addEventListener('click', handleToggleGraphs, false);
};

const handleToggleSimulation = (event) => {
  DRAW_SIMULATION = !DRAW_SIMULATION;
  event.target.innerHTML = DRAW_SIMULATION ? 'Hide' : 'Show';
  event.target.style.background = DRAW_SIMULATION ? 'green' : 'red';
};

const handleToggleGraphs = (event) => {
  DRAW_GRAPHS = !DRAW_GRAPHS;
  event.target.innerHTML = DRAW_GRAPHS ? 'Hide' : 'Show';
  event.target.style.background = DRAW_GRAPHS ? 'green' : 'red';
};