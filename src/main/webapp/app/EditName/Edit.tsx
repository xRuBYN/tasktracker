import React, { useState } from 'react';
import { Button, Modal, Form } from 'react-bootstrap';

const EditProjectModal = ({ project, accessToken, onProjectNameChange, onModalClose, onProjectUpdate }) => {
  const [newProjectName, setNewProjectName] = useState(project.name);

  const handleProjectNameChange = event => {
    setNewProjectName(event.target.value);
  };

  const handleSubmit = async event => {
    event.preventDefault();

    try {
      const response = await fetch(`http://localhost:8080/api/project/edit/${project.id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          Authorization: accessToken,
        },
        body: JSON.stringify({
          name: newProjectName,
        }),
      });

      if (response.ok) {
        const updatedProject = await response.json();
        console.log('Project updated:', updatedProject);
        onProjectUpdate(updatedProject);
        onModalClose();
      } else {
        console.error('Request failed with status:', response.status);
      }
    } catch (error) {
      console.error('An error occurred:', error);
    }
  };

  return (
    <Modal show={true} onHide={onModalClose}>
      <Modal.Header closeButton>
        <Modal.Title>Edit Project</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form onSubmit={handleSubmit}>
          <Form.Group controlId="newProjectName">
            <Form.Label>New Project Name</Form.Label>
            <Form.Control type="text" value={newProjectName} onChange={handleProjectNameChange} required />
          </Form.Group>
          <Modal.Footer>
            <Button variant="primary" type="submit">
              Save
            </Button>
            <Button variant="secondary" onClick={onModalClose}>
              Cancel
            </Button>
          </Modal.Footer>
        </Form>
      </Modal.Body>
    </Modal>
  );
};

export default EditProjectModal;
