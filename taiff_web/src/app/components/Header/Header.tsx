import logo from './logo.png';

export function Header(props: any) { 
    return (
        <div> 
            <header> <img src="{logo}" alt="" /> </header>
            {props.children}
        </div>
    );
}