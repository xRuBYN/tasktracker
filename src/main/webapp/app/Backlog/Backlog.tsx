import React, { useState } from 'react';
import { Translate } from 'react-jhipster';
import { Row, Col, Form, FormGroup, Label, Input, Button } from 'reactstrap';

const Backlog = () => {
  const [issueType, setIssueType] = useState('');
  const [description, setDescription] = useState('');
  const [workflow, setWorkflow] = useState('');
  const [backlogItems, setBacklogItems] = useState([]);

  const handleIssueTypeChange = event => {
    setIssueType(event.target.value);
  };

  const handleDescriptionChange = event => {
    setDescription(event.target.value);
  };

  const handleWorkflowChange = event => {
    setWorkflow(event.target.value);
  };

  const handleSubmit = event => {
    event.preventDefault();

    const backlogItem = {
      issueType,
      description,
      workflow,
    };

    setBacklogItems([...backlogItems, backlogItem]);
    setIssueType('');
    setDescription('');
    setWorkflow('');
  };

  return (
    <Row>
      <Col md="6">
        <h2>
          <Translate contentKey="backlog.title">Backlog</Translate>
        </h2>
        <hr />
        <Form onSubmit={handleSubmit}>
          <FormGroup>
            <Label for="issueType">
              <Translate contentKey="backlog.issueType">Issue Type</Translate>
            </Label>
            <Input type="select" name="issueType" id="issueType" value={issueType} onChange={handleIssueTypeChange} required>
              <option value="">-- Select Issue Type --</option>
              <option value="bug">Bug</option>
              <option value="task">Task</option>
            </Input>
          </FormGroup>
          <FormGroup>
            <Label for="description">
              <Translate contentKey="backlog.description">Description</Translate>
            </Label>
            <Input type="textarea" name="description" id="description" value={description} onChange={handleDescriptionChange} required />
          </FormGroup>
          <FormGroup>
            <Label for="workflow">
              <Translate contentKey="backlog.workflow">Workflow</Translate>
            </Label>
            <Input type="select" name="workflow" id="workflow" value={workflow} onChange={handleWorkflowChange} required>
              <option value="">-- Select Workflow --</option>
              <option value="todo">To Do</option>
              <option value="inprogress">In Progress</option>
              <option value="done">Done</option>
              <option value="review">In Review</option>
            </Input>
          </FormGroup>
          <Button color="primary" type="submit">
            <Translate contentKey="backlog.save">Save</Translate>
          </Button>
        </Form>

        <hr />

        <h3>Backlog Items:</h3>
        <ul>
          {backlogItems.map((item, index) => (
            <li key={index}>
              <strong>Issue Type:</strong> {item.issueType}
              <br />
              <strong>Description:</strong> {item.description}
              <br />
              <strong>Workflow:</strong> {item.workflow}
            </li>
          ))}
        </ul>
      </Col>
    </Row>
  );
};

export default Backlog;
