import React, { useEffect, useState } from 'react';

interface AddColumnModalProps {
  projectId: string;
}

const AddColumnModal: React.FC<AddColumnModalProps> = ({ projectId }) => {
  const [isOpen, setIsOpen] = useState(false);
  const [workflow, setWorkflow] = useState('');

  useEffect(() => {
    if (!projectId) {
      console.log('Token-ul sau id-ul proiectului lipsește din stocare.');
      // Aici puteți adăuga logica pentru afișarea unui mesaj de eroare sau redirecționarea utilizatorului
    }
  }, [projectId]);

  const handleOpenModal = () => {
    if (projectId) {
      setIsOpen(true);
    } else {
      console.log('Token-ul sau id-ul proiectului lipsește din stocare.');
      // Aici puteți adăuga logica pentru afișarea unui mesaj de eroare sau redirecționarea utilizatorului
    }
  };

  const handleCloseModal = () => {
    setIsOpen(false);
    setWorkflow('');
  };

  const handleWorkflowChange = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    setWorkflow(e.target.value);
  };

  const handleAddColumn = () => {
    // Verificăm token-ul în sessionStorage și id-ul proiectului în localStorage
    const token = sessionStorage.getItem('token');
    const projectIdFromStorage = localStorage.getItem('projectId');

    if (!token || !projectIdFromStorage) {
      console.log('Token-ul sau id-ul proiectului lipsește din stocare.');
      return;
    }

    // Construim URL-ul API-ului cu metoda POST
    const url = `http://localhost:8080/api/column/${projectId}`;

    // Construim obiectul pentru corpul cererii
    const columnData = {
      workflow: workflow,
      token: token,
      projectId: projectIdFromStorage,
    };

    // Efectuăm cererea POST către server
    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(columnData),
    })
      .then(response => {
        if (response.ok) {
          // Procesăm răspunsul dacă este necesar
          console.log('Coloana a fost adăugată cu succes!');
          handleCloseModal();
        } else {
          console.log('A apărut o eroare la adăugarea coloanei.');
        }
      })
      .catch(error => {
        console.log('A apărut o eroare la adăugarea coloanei:', error);
      });
  };

  return (
    <div>
      <button onClick={handleOpenModal}>Adaugă coloană</button>
      {isOpen && (
        <div>
          <div>
            <textarea value={workflow} onChange={handleWorkflowChange} />
          </div>
          <button onClick={handleAddColumn}>Adaugă</button>
          <button onClick={handleCloseModal}>Anulează</button>
        </div>
      )}
    </div>
  );
};

export default AddColumnModal;
