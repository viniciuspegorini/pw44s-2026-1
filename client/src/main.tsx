import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from '@/App.tsx'

import "primereact/resources/themes/lara-light-indigo/theme.css"; //theme
import "primereact/resources/primereact.min.css"; //core css
import { PrimeReactProvider } from "primereact/api";
import 'primeicons/primeicons.css'
import 'primeflex/primeflex.css';
import { BrowserRouter } from 'react-router-dom';
import { AuthProvider } from '@/context/AuthContext.tsx';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <BrowserRouter>
      <PrimeReactProvider>
        <AuthProvider>
          <App />
        </AuthProvider>
      </PrimeReactProvider>
    </BrowserRouter>
  </StrictMode>,
)
