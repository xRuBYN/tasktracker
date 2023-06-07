import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles.scss';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

interface Observer {
  update: (action: string) => void;
}

interface Subject {
  attach: (observer: Observer) => void;
  detach: (observer: Observer) => void;
  notify: (action: string) => void;
}

const Home: React.FC = () => {
  const [projectName, setProjectName] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [projects, setProjects] = useState([]);
  const [selectedProjectId, setSelectedProjectId] = useState(null);

  const [notificationObservers, setNotificationObservers] = useState<Observer[]>([]);
  const [alerts, setAlerts] = useState<string[]>([]);

  const handleAlert = (action: string) => {
    toast(action, { autoClose: 3000 });
  };

  const projectSubject: Subject = {
    attach(observer: Observer) {
      setNotificationObservers(prevObservers => [...prevObservers, observer]);
    },
    detach(observer: Observer) {
      setNotificationObservers(prevObservers => prevObservers.filter(prevObserver => prevObserver !== observer));
    },
    notify(action: string) {
      notificationObservers.forEach(observer => observer.update(action));
    },
  };

  const handleCreateProject = () => {
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setSelectedProjectId(null);
  };

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    const token = sessionStorage.getItem('token');

    try {
      if (selectedProjectId) {
        const response = await axios.put(
          `http://localhost:8080/api/project/edit/${selectedProjectId}`,
          { name: projectName },
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        const updatedProject = { id: response.data.id, name: projectName };

        const updatedProjects = projects.map(project => (project.id === selectedProjectId ? updatedProject : project));
        localStorage.setItem('projects', JSON.stringify(updatedProjects));

        setProjects(updatedProjects);

        projectSubject.notify('edit_project');
      } else {
        const response = await axios.post(
          'http://localhost:8080/api/project/create',
          { name: projectName },
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        const project = { id: response.data.id, name: projectName };

        const projectsFromStorage = JSON.parse(localStorage.getItem('projects') || '[]');
        projectsFromStorage.push(project);
        localStorage.setItem('projects', JSON.stringify(projectsFromStorage));

        setProjects([...projects, project]);

        projectSubject.notify('create_project');
      }

      setProjectName('');
      setIsModalOpen(false);
      setSelectedProjectId(null);
    } catch (error) {
      console.error('A apărut o eroare la crearea/editarea proiectului:', error);
    }
  };

  const handleDeleteProject = projectId => {
    const updatedProjects = projects.filter(project => project.id !== projectId);

    setProjects(updatedProjects);

    localStorage.setItem('projects', JSON.stringify(updatedProjects));

    projectSubject.notify('delete_project');
  };

  const fetchProjectsFromLocalStorage = () => {
    const projectsFromStorage = JSON.parse(localStorage.getItem('projects') || '[]');
    setProjects(projectsFromStorage);
  };

  useEffect(() => {
    fetchProjectsFromLocalStorage();
  }, []);

  const handleEditProject = projectId => {
    const projectToEdit = projects.find(project => project.id === projectId);
    setProjectName(projectToEdit.name);
    setSelectedProjectId(projectId);
    setIsModalOpen(true);
  };

  const notificationObserver: Observer = {
    update(action: string) {
      console.log(`Received notification for action: ${action}`);
      handleAlert(`Received notification: ${action}`);
    },
  };

  useEffect(() => {
    projectSubject.attach(notificationObserver);

    return () => {
      projectSubject.detach(notificationObserver);
    };
  }, []);

  return (
    <div className="home">
      <ToastContainer />

      <h1>Task-Tracker</h1>
      <button onClick={handleCreateProject}>Create</button>

      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal">
            <span className="close" onClick={handleCloseModal}>
              &times;
            </span>
            <form onSubmit={handleSubmit}>
              <label>
                Name:
                <input type="text" value={projectName} onChange={event => setProjectName(event.target.value)} />
              </label>
              <button type="submit">Save</button>
            </form>
          </div>
        </div>
      )}

      <h2>My Projects</h2>
      <ul>
        {projects.map(project => (
          <li key={project.id}>
            {project.name}
            <button className="edit-button" onClick={() => handleEditProject(project.id)}>
              Edit
            </button>
            <button className="delete-button" onClick={() => handleDeleteProject(project.id)}>
              Delete
            </button>
          </li>
        ))}
      </ul>
      <div className="alerts">
        {alerts.map((alert, index) => (
          <div className="alert" key={index}>
            {alert}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
