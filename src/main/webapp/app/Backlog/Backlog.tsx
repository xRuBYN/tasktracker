import React, { useState } from 'react';
import { Translate } from 'react-jhipster';
import { Row, Col, Form, FormGroup, Label, Input, Button } from 'reactstrap';

const Backlog = () => {
  const [issueType, setIssueType] = useState('');
  const [description, setDescription] = useState('');

  const handleIssueTypeChange = event => {
    setIssueType(event.target.value);
  };

  const handleDescriptionChange = event => {
    setDescription(event.target.value);
  };

  const handleSubmit = event => {
    event.preventDefault();
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
          <Button color="primary" type="submit">
            <Translate contentKey="backlog.save">Save</Translate>
          </Button>
        </Form>
      </Col>
    </Row>
  );
};

export default Backlog;
