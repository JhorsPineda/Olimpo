// src/app/login/page.js
'use client';

import { useState, useEffect } from 'react';
import axios from 'axios';

// Función para obtener el token CSRF
const getCsrfToken = async () => {
  try {
    const response = await axios.get('/api/csrf-token', { withCredentials: true });
    console.log('CSRF Token Response:', response.data); // Verificar la respuesta del token CSRF
    return response.data.token; // Ajusta esto según la estructura de tu respuesta
  } catch (error) {
    console.error('Error obteniendo el token CSRF', error);
    return null;
  }
};

export default function Login() {
  const [documentType, setDocumentType] = useState('');
  const [documentNumber, setDocumentNumber] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [csrfToken, setCsrfToken] = useState('');

  // Obtener el token CSRF cuando el componente se monta
  useEffect(() => {
    const fetchToken = async () => {
      const token = await getCsrfToken();
      console.log('CSRF Token:', token); // Verificar que el token se obtiene correctamente
      setCsrfToken(token);
    };
    fetchToken();
  }, []);

  // Función para manejar el inicio de sesión
  const handleLogin = async (e) => {
    e.preventDefault();
    setError(''); // Limpiar el error al iniciar una nueva solicitud

    if (csrfToken) {
      console.log('Login Payload:', { documentType, documentNumber, password }); // Verificar los datos que se están enviando

      try {
        const response = await axios.post('/api/auth/login', 
        {
          documentType,
          documentNumber,
          password,
        }, 
        {
          headers: {
            'x-csrf-token': csrfToken, // Usar el token CSRF obtenido del backend
          },
          withCredentials: true, // Esto asegura que las cookies se envíen con la solicitud
        });

        console.log('Login Response:', response.data); // Verificar la respuesta del login

        if (response.status === 200 && response.data.status === 'success') {
          window.location.href = response.data.data.redirectUrl;
        } else {
          setError(response.data.message || 'Credenciales inválidas');
        }
      } catch (err) {
        console.error('Error durante el inicio de sesión', err); // Para depurar cualquier error
        if (err.response) {
          console.error('Error Response:', err.response); // Mostrar respuesta de error completa
          setError(err.response.data?.message || 'Credenciales inválidas');
        } else {
          setError('Error de red o problema en el servidor');
        }
      }
    } else {
      setError('No se pudo obtener el token CSRF');
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="max-w-md w-full bg-white p-8 rounded shadow">
        <h1 className="text-2xl font-bold mb-6 text-center">Login</h1>
        <form onSubmit={handleLogin}>
          <div className="mb-4">
            <label className="block mb-1 text-black">Tipo de Documento</label>
            <input
              type="text"
              className="w-full p-2 text-black border border-gray-300 rounded"
              value={documentType}
              onChange={(e) => setDocumentType(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1 text-black">Número de Documento</label>
            <input
              type="text"
              className="w-full p-2 text-black border border-gray-300 rounded"
              value={documentNumber}
              onChange={(e) => setDocumentNumber(e.target.value)}
              required
            />
          </div>
          <div className="mb-4">
            <label className="block mb-1 text-black">Contraseña</label>
            <input
              type="password"
              className="w-full p-2 text-black border border-gray-300 rounded"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          {error && <p className="text-red-500 text-center mb-4">{error}</p>}
          <button
            type="submit"
            className="w-full p-2 bg-blue-500 text-white rounded hover:bg-blue-600"
          >
            Iniciar Sesión
          </button>
        </form>
      </div>
    </div>
  );
}
