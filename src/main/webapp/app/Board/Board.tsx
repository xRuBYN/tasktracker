import React, { useState } from 'react';
import { Container, Button, Modal, Form } from 'react-bootstrap';

const MainPage: React.FC = () => {
  const [showModal, setShowModal] = useState(false);
  const [username, setUsername] = useState('');
  const [userId, setUserId] = useState(0);

  const handleShowModal = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleSearchUser = async () => {
    try {
      const response = await fetch(`http://localhost:8080/api/admin/_search/users?query=${username}&page=0&size=1`, {
        headers: {
          Authorization:
            'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY4NTk2NTUwM30.A77yFmYEW7WBJihKr1B0vwzlNjYf7hyuCp8NNzvOd4aKBfYLXF3A1X2A7zC5dKVej4Fu6yJpZvgSvOJ95EIpNA',
        },
      });

      if (response.ok) {
        const data = await response.json();
        const user = data[0];

        if (user) {
          setUserId(user.id);
        } else {
          console.error('Utilizatorul nu a fost găsit');
        }
      } else {
        console.error('A apărut o eroare la căutarea utilizatorului');
      }
    } catch (error) {
      console.error('A apărut o eroare la căutarea utilizatorului:', error);
    }
  };

  const handleAddParticipant = async () => {
    try {
      const requestBody = [userId];
      const accessToken =
        'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY4NTk2NTUwM30.A77yFmYEW7WBJihKr1B0vwzlNjYf7hyuCp8NNzvOd4aKBfYLXF3A1X2A7zC5dKVej4Fu6yJpZvgSvOJ95EIpNA';

      const response = await fetch('http://localhost:8080/api/project/add/', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: accessToken,
        },
        body: JSON.stringify(requestBody),
      });

      if (response.ok) {
        console.log('Participantul a fost adăugat cu succes');
      } else {
        console.error('A apărut o eroare la adăugarea participantului');
      }
    } catch (error) {
      console.error('A apărut o eroare la adăugarea participantului:', error);
    }
  };

  return (
    <Container>
      <h1>Board</h1>
      <Button variant="primary" onClick={handleShowModal}>
        Assign Person
      </Button>
      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>Add participant</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Group>
            <Form.Label>Name of user:</Form.Label>
            <Form.Control type="text" placeholder="Enter name of user" value={username} onChange={e => setUsername(e.target.value)} />
          </Form.Group>
          <Button variant="primary" onClick={handleSearchUser}>
            Search
          </Button>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            Close
          </Button>
          <Button variant="primary" onClick={handleAddParticipant}>
            Add
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
};

export default MainPage;
