// src/hooks/useCsrfToken.js

import { useState, useEffect } from 'react';
import axios from 'axios';

function useCsrfToken() {
  const [csrfToken, setCsrfToken] = useState('');

  useEffect(() => {
    async function fetchCsrfToken() {
      try {
        const response = await axios.get('http://localhost:8081/api/csrf-token', { withCredentials: true });
        setCsrfToken(response.data.token);
      } catch (error) {
        console.error('Error fetching CSRF token:', error);
      }
    }

    fetchCsrfToken();
  }, []);

  return csrfToken;
}

export default useCsrfToken;
