import { Header } from './app/components/Header/Header';
import { Search } from './app/components/Search/Search';
import { ButtonTaiff } from './app/components/ButtonTaiff/ButtonTaiff';


function App() {
  return (
    <section>
      <Header/>
      <ButtonTaiff name="Teste" /> 
      <Search/>
    </section> 
      
  );
}

export default App;
