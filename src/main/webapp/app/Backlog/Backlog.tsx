import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Backlog: React.FC = () => {
  const [priority, setPriority] = useState('');
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [selectedProject, setSelectedProject] = useState('');
  const [projects, setProjects] = useState([]);

  useEffect(() => {
    const storedProjects = JSON.parse(localStorage.getItem('projects')) || [];
    setProjects(storedProjects);
  }, []);

  const handlePriorityChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPriority(event.target.value);
  };

  const handleNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setName(event.target.value);
  };

  const handleDescriptionChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    setDescription(event.target.value);
  };

  const handleProjectChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedProject(event.target.value);
  };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    const columnId = localStorage.getItem('columnId');
    if (columnId && selectedProject) {
      const apiUrl = `http://localhost:8080/api/issue/${columnId}`;
      try {
        const response = await axios.post(apiUrl, {
          project: selectedProject,
          priority,
          name,
          description,
        });
        // Issue-ul a fost adăugat cu succes
        console.log(response.data);
      } catch (error) {
        console.error('A apărut o eroare la adăugarea issue-ului:', error);
      }
    }
  };

  return (
    <div>
      <h1>Adaugă Issue</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>
            Proiect:
            <select value={selectedProject} onChange={handleProjectChange}>
              <option value="">Selectează un proiect</option>
              {projects.map((project, index) => (
                <option key={project.id} value={index}>
                  {project.name}
                </option>
              ))}
            </select>
          </label>
        </div>
        <div>
          <label>
            Prioritate:
            <input type="text" value={priority} onChange={handlePriorityChange} />
          </label>
        </div>
        <div>
          <label>
            Nume:
            <input type="text" value={name} onChange={handleNameChange} />
          </label>
        </div>
        <div>
          <label>
            Descriere:
            <textarea value={description} onChange={handleDescriptionChange} />
          </label>
        </div>
        <button type="submit">Adaugă</button>
      </form>
    </div>
  );
};

export default Backlog;
