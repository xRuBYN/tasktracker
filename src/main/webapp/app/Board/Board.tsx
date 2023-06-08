import React, { useState } from 'react';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import './board.css';
import { AiFillDelete } from 'react-icons/ai';

interface Column {
  id: number;
  name: string;
  status: string;
  color: string;
}

interface Issue {
  id: number;
  name: string;
  priority: string;
  description: string;
  columnId: number;
}

const App: React.FC = () => {
  const [columns, setColumns] = useState<Column[]>([]);
  const [columnName, setColumnName] = useState('');
  const [issues, setIssues] = useState<Issue[]>([]);
  const [issueName, setIssueName] = useState('');
  const [issuePriority, setIssuePriority] = useState('');
  const [issueDescription, setIssueDescription] = useState('');
  const [draggedColumnId, setDraggedColumnId] = useState<number | null>(null);

  const handleAddColumn = () => {
    if (columnName) {
      let newColumn: Column;
      if (columnName.toLowerCase() === 'to do') {
        newColumn = {
          id: Date.now(),
          name: columnName,
          status: 'To Do',
          color: 'grey',
        };
      } else if (columnName.toLowerCase() === 'in progress') {
        newColumn = {
          id: Date.now(),
          name: columnName,
          status: 'In Progress',
          color: 'blue',
        };
      } else if (columnName.toLowerCase() === 'done') {
        newColumn = {
          id: Date.now(),
          name: columnName,
          status: 'Done',
          color: 'green',
        };
      } else {
        newColumn = {
          id: Date.now(),
          name: columnName,
          status: 'Unknown',
          color: 'black',
        };
      }

      setColumns([...columns, newColumn]);
      setColumnName('');
    }
  };

  const handleDeleteColumn = (columnId: number) => {
    const updatedColumns = columns.filter(column => column.id !== columnId);
    setColumns(updatedColumns);
    const updatedIssues = issues.filter(issue => issue.columnId !== columnId);
    setIssues(updatedIssues);
  };

  const handleAddIssue = () => {
    if (issueName && issuePriority && issueDescription) {
      const newIssue: Issue = {
        id: Date.now(),
        name: issueName,
        priority: issuePriority,
        description: issueDescription,
        columnId: columns[0].id,
      };
      setIssues([...issues, newIssue]);
      setIssueName('');
      setIssuePriority('');
      setIssueDescription('');
    }
  };

  const handleDragStart = (columnId: number) => {
    setDraggedColumnId(columnId);
  };

  const handleDragOver = (event: React.DragEvent<HTMLDivElement>) => {
    event.preventDefault();
  };

  const handleDrop = (event: React.DragEvent<HTMLDivElement>, targetColumnId: number) => {
    event.preventDefault();
    if (draggedColumnId !== null && draggedColumnId !== targetColumnId) {
      const updatedIssues = issues.map(issue => {
        if (issue.columnId === draggedColumnId) {
          return {
            ...issue,
            columnId: targetColumnId,
          };
        }
        return issue;
      });
      setIssues(updatedIssues);
    }
    setDraggedColumnId(null);
  };

  return (
    <Container>
      <Row>
        <Col>
          <h1>Board</h1>
        </Col>
      </Row>
      <Row>
        <Col>
          <Form>
            <Form.Group controlId="columnName">
              <Form.Label>Column Name</Form.Label>
              <Form.Control type="text" value={columnName} onChange={e => setColumnName(e.target.value)} />
            </Form.Group>
            <Button variant="primary" onClick={handleAddColumn}>
              Add Column
            </Button>
          </Form>
        </Col>
      </Row>
      <div className="column-container">
        {columns.map(column => (
          <div
            key={column.id}
            className="column"
            draggable
            onDragStart={() => handleDragStart(column.id)}
            onDragOver={handleDragOver}
            onDrop={event => handleDrop(event, column.id)}
            style={{ backgroundColor: column.color }}
          >
            <h3>{column.name}</h3>
            <Button variant="danger" className="delete-button" onClick={() => handleDeleteColumn(column.id)}>
              <AiFillDelete className="delete-icon" />
            </Button>

            <div className="issue-container">
              {issues
                .filter(issue => issue.columnId === column.id)
                .map(issue => (
                  <div key={issue.id} className="issue">
                    <h4>{issue.name}</h4>
                    <p>Priority: {issue.priority}</p>
                    <p>{issue.description}</p>
                  </div>
                ))}
            </div>
          </div>
        ))}
      </div>
      <Row>
        <Col>
          <Form>
            <Form.Group controlId="issueName">
              <Form.Label>Issue Name</Form.Label>
              <Form.Control type="text" value={issueName} onChange={e => setIssueName(e.target.value)} />
            </Form.Group>
            <Form.Group controlId="issuePriority">
              <Form.Label>Priority</Form.Label>
              <Form.Control type="text" value={issuePriority} onChange={e => setIssuePriority(e.target.value)} />
            </Form.Group>
            <Form.Group controlId="issueDescription">
              <Form.Label>Description</Form.Label>
              <Form.Control as="textarea" value={issueDescription} onChange={e => setIssueDescription(e.target.value)} />
            </Form.Group>
            <Button variant="primary" onClick={handleAddIssue}>
              Add Issue
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default App;
