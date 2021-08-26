import { Header } from './app/components/Header/Header';
import { Search } from './app/components/Search/Search';
import { ButtonTaiff } from './app/components/ButtonTaiff/ButtonTaiff';


function App() {
  return (
    <section>
      <Header/>
      <ButtonTaiff name="teste1" verbo="teste1"/>
      <ButtonTaiff name="teste2" verbo="teste2"/>
      <Search/>
    </section> 
      
  );
}

export default App;
