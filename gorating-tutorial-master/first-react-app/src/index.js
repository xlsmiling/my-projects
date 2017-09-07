import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import ClickCounters from './Componets/ClickCounters'

ReactDOM.render(<ClickCounters value={1} />, document.getElementById('root'));
registerServiceWorker();
