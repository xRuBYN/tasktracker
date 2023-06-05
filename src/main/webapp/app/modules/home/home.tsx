import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Button, Modal, Form, ListGroup, Dropdown } from 'react-bootstrap';
import EditProjectModal from 'app/EditName/Edit';
const Home = () => {
  const [modal, setModal] = useState(false);
  const [projectName, setProjectName] = useState('');
  const [projects, setProjects] = useState([]);
  const [editModalOpen, setEditModalOpen] = useState(false);
  const [selectedProject, setSelectedProject] = useState(null);

  useEffect(() => {
    // Fetch projects from localStorage on component mount
    const storedProjects = localStorage.getItem('projects');
    if (storedProjects) {
      setProjects(JSON.parse(storedProjects));
    }
  }, []);

  useEffect(() => {
    // Update localStorage when projects state changes
    localStorage.setItem('projects', JSON.stringify(projects));
  }, [projects]);

  const toggleModal = () => {
    setModal(!modal);
  };

  const handleSubmit = async event => {
    event.preventDefault();

    try {
      const response = await fetch('http://localhost:8080/api/project/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: accessToken, // Utilizează variabila accessToken aici
        },
        body: JSON.stringify({
          name: projectName,
        }),
      });

      if (response.ok) {
        const project = await response.json();
        console.log('Project created:', project);
        setProjects([...projects, project]); // Add the newly created project to the projects state
        toggleModal();
      } else {
        console.error('Request failed with status:', response.status);
      }
    } catch (error) {
      console.error('An error occurred:', error);
    }
  };

  const handleModifyProject = projectId => {
    console.log('Modify project:', projectId);
    const projectToEdit = projects.find(project => project.id === projectId);
    if (projectToEdit) {
      setSelectedProject(projectToEdit);
      setEditModalOpen(true);
    }
  };

  const handleProjectNameChange = event => {
    setSelectedProject(prevProject => ({
      ...prevProject,
      name: event.target.value,
    }));
  };

  const handleProjectUpdate = updatedProject => {
    setProjects(prevProjects => {
      const updatedProjects = prevProjects.map(project => (project.id === updatedProject.id ? updatedProject : project));
      return updatedProjects;
    });
  };

  const accessToken =
    'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY4NTk2NTUwM30.A77yFmYEW7WBJihKr1B0vwzlNjYf7hyuCp8NNzvOd4aKBfYLXF3A1X2A7zC5dKVej4Fu6yJpZvgSvOJ95EIpNA';
  return (
    <Container>
      <Row>
        <Col md="6">
          <h2>Welcome!</h2>
          <hr />
          <Button variant="primary" onClick={toggleModal}>
            Create Project
          </Button>
        </Col>
      </Row>

      <Modal show={modal} onHide={toggleModal}>
        <Modal.Header closeButton>
          <Modal.Title>Create Project</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="projectName">
              <Form.Label>Project Name</Form.Label>
              <Form.Control type="text" value={projectName} onChange={handleProjectNameChange} required />
            </Form.Group>
            <Modal.Footer>
              <Button variant="primary" type="submit">
                Save
              </Button>
              <Button variant="secondary" onClick={toggleModal}>
                Cancel
              </Button>
            </Modal.Footer>
          </Form>
        </Modal.Body>
      </Modal>

      <Row>
        <Col md="6">
          <h3>Projects</h3>
          <ListGroup>
            {projects.map(project => (
              <ListGroup.Item key={project.id} className="d-flex justify-content-between align-items-center">
                <span>{project.name}</span>
                <Dropdown>
                  <Dropdown.Toggle variant="secondary" id={`dropdown-${project.id}`}>
                    ...
                  </Dropdown.Toggle>
                  <Dropdown.Menu>
                    <Dropdown.Item onClick={() => handleModifyProject(project.id)}>Modify</Dropdown.Item>
                  </Dropdown.Menu>
                </Dropdown>
              </ListGroup.Item>
            ))}
          </ListGroup>
        </Col>
      </Row>

      {editModalOpen && (
        <EditProjectModal
          project={selectedProject}
          accessToken={accessToken}
          onModalClose={() => setEditModalOpen(false)}
          onProjectNameChange={handleProjectNameChange}
          onProjectUpdate={handleProjectUpdate}
        />
      )}
    </Container>
  );
};

export default Home;
