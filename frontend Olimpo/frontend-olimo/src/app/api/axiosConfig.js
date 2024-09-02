// src/api/axiosConfig.js

import axios from 'axios';
import { useEffect, useState } from 'react';

export function useAxios() {
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

  const axiosInstance = axios.create({
    baseURL: 'http://localhost:8081',
    headers: {
      'X-XSRF-TOKEN': csrfToken,
    },
    withCredentials: true,
  });

  return axiosInstance;
}
