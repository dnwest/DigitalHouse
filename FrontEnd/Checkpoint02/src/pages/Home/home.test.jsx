import { fireEvent, getByRole, getByText, render, screen } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import axios from 'axios';
import Card from '.';
import App from '.';

beforeEach(() => {
    jest.mock(axios);
});

describe ('<Card />', () => { 



    test("Testar se o card de Dentista está aparecendo corretamente na tela de início", ()=>{

        render(
            <BrowserRouter>
                <Card
                    nome="Admin"
                    sobrenome="Admin"
                />
            </BrowserRouter>)

        const nome = getByRole("heading", { name: "Admin Admin"});

        expect(nome).toBeInTheDocument();

    });

    test("Testar se aparece o Logout quando logado", ()=>{

        render(
            <BrowserRouter>
                <Navbar />
            </BrowserRouter>)

        const logout = getByText("Lougout");

        expect(logout).toBeInTheDocument();

    });

})

describe ('<App />', () => { 


    test("Testar troca de temas", ()=>{

        render(
            <BrowserRouter>
                <App />
            </BrowserRouter>
        )

        const light = screen.findAllByText('light');

        expect(light).toBeInTheDocument();

        const button = screen.getByRole ('button', { name: '🌙'});

        fireEvent.click(button);

        const dark = screen.findAllByText('dark');

        expect(dark).toBeInTheDocument();

    });



})