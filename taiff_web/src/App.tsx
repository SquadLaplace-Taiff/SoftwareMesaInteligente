import React from 'react';
import HelloWorld from './app/components/HelloWorld';
import { Header } from './app/components/Header/Header';

function App() {
  return (
    <div>
      <Header > 
        <HelloWorld /> 
      </Header>
    </div>
  );
}

export default App;
