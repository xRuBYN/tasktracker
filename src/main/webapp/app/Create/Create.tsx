import React, { useState } from 'react';

const Create = ({ token }) => {
  const [projectName, setProjectName] = useState('');

  const createProject = () => {
    fetch('http://localhost:8080/api/project/create', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify({ name: projectName }),
    })
      .then(response => response.json())
      .then(data => {})
      .catch(error => {
        console.error('Error:', error);
      });
  };

  return (
    <div>
      <h2>Create Project</h2>
      <input type="text" value={projectName} onChange={event => setProjectName(event.target.value)} placeholder="Project Name" />
      <button onClick={createProject}>Create</button>
    </div>
  );
};

export default Create;
