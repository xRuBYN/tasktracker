import React, { useState } from 'react';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import './board.css';

interface Column {
  id: number;
  name: string;
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

  const handleAddColumn = () => {
    if (columnName) {
      const newColumn: Column = {
        id: Date.now(),
        name: columnName,
      };
      setColumns([...columns, newColumn]);
      setColumnName('');
    }
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
          <div key={column.id} className="column">
            <h3>{column.name}</h3>
            {issues
              .filter(issue => issue.columnId === column.id)
              .map(issue => (
                <div key={issue.id}>
                  <h4>{issue.name}</h4>
                  <p>Priority: {issue.priority}</p>
                  <p>{issue.description}</p>
                </div>
              ))}
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
