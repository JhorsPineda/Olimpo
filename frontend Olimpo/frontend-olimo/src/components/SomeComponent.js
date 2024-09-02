// src/components/SomeComponent.js

import React, { useEffect, useState } from 'react';
import { useAxios } from '../api/axiosConfig';

function SomeComponent() {
  const axiosInstance = useAxios();
  const [data, setData] = useState(null);

  useEffect(() => {
    async function fetchData() {
      try {
        const response = await axiosInstance.get('/api/protected-endpoint');
        setData(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }

    fetchData();
  }, [axiosInstance]);

  return (
    <div>
      {/* Renderiza los datos o algún mensaje */}
      {data ? <div>Data: {JSON.stringify(data)}</div> : <div>Loading...</div>}
    </div>
  );
}

export default SomeComponent;
