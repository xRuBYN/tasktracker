import './home.scss';

import React, { useState } from 'react';
import { Translate } from 'react-jhipster';
import { Row, Col, Alert, Button, Modal, ModalHeader, ModalBody, ModalFooter, Form, FormGroup, Label, Input } from 'reactstrap';
import { useAppSelector } from 'app/config/store';
import { TranslatorContext } from 'react-jhipster';
import { Link, useNavigate } from 'react-router-dom';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);
  const [modal, setModal] = useState(false);
  const [projectName, setProjectName] = useState('');
  const [issueType, setIssueType] = useState('');
  const [workflow, setWorkflow] = useState('');
  const navigate = useNavigate();

  const toggleModal = () => {
    setModal(!modal);
  };

  const handleProjectNameChange = event => {
    setProjectName(event.target.value);
  };

  const handleIssueTypeChange = event => {
    setIssueType(event.target.value);
  };

  const handleWorkflowChange = event => {
    setWorkflow(event.target.value);
  };

  const handleSubmit = event => {
    event.preventDefault();
    navigate('/backlog');

    toggleModal();
  };

  return (
    <Row>
      <Col md="6">
        <h2>
          <Translate contentKey="home.title">Welcome, {account.login}!</Translate>
        </h2>
        <hr />
        <Alert color="info">
          <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
            You are logged in as user {account.login}.
          </Translate>
        </Alert>
        <Button color="primary" onClick={toggleModal}>
          <Translate contentKey="home.createProject">Create Project</Translate>
        </Button>
      </Col>

      <Modal isOpen={modal} toggle={toggleModal}>
        <ModalHeader toggle={toggleModal}>
          <Translate contentKey="home.createProject">Create Project</Translate>
        </ModalHeader>
        <ModalBody>
          <Form onSubmit={handleSubmit}>
            <FormGroup>
              <Label for="projectName">
                <Translate contentKey="home.projectName">Project Name</Translate>
              </Label>
              <Input type="text" name="projectName" id="projectName" value={projectName} onChange={handleProjectNameChange} required />
            </FormGroup>
            <FormGroup>
              <Label for="issueType">
                <Translate contentKey="home.issueType">Issue Type</Translate>
              </Label>
              <Input type="select" name="issueType" id="issueType" value={issueType} onChange={handleIssueTypeChange} required>
                <option value="">-- Select Issue Type --</option>
                <option value="bug">Bug</option>
                <option value="feature">Feature</option>
                <option value="task">Task</option>
              </Input>
            </FormGroup>
            <FormGroup>
              <Label for="workflow">
                <Translate contentKey="home.workflow">Workflow</Translate>
              </Label>
              <Input type="select" name="workflow" id="workflow" value={workflow} onChange={handleWorkflowChange} required>
                <option value="">-- Select Workflow --</option>
                <option value="todo">To Do</option>
                <option value="inprogress">In Progress</option>
                <option value="done">Done</option>
                <option value="review">In Review</option>
              </Input>
            </FormGroup>
            <ModalFooter>
              <Button color="primary" type="submit">
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
              <Button color="secondary" onClick={toggleModal}>
                <Translate contentKey="entity.action.cancel">Cancel</Translate>
              </Button>
            </ModalFooter>
          </Form>
        </ModalBody>
      </Modal>
    </Row>
  );
};

TranslatorContext.setDefaultLocale('en');
TranslatorContext.setRenderInnerTextForMissingKeys(false);

export default Home;
