import React, { useState } from 'react';
import { Button, Form, FormGroup, Label, Input } from 'reactstrap';

const CreateProjectPage = () => {
  const [projectName, setProjectName] = useState('');
  const [issueType, setIssueType] = useState('');
  const [workflow, setWorkflow] = useState('');

  const handleProjectNameChange = e => {
    setProjectName(e.target.value);
  };

  const handleIssueTypeChange = e => {
    setIssueType(e.target.value);
  };

  const handleWorkflowChange = e => {
    setWorkflow(e.target.value);
  };

  const handleSubmit = e => {
    e.preventDefault();

    // eslint-disable-next-line no-console
    console.log('Trimiteți proiectul la server:', {
      projectName,
      issueType,
      workflow,
    });

    setProjectName('');
    setIssueType('');
    setWorkflow('');
  };

  return (
    <div>
      <h1>Create Project</h1>
      <Form onSubmit={handleSubmit}>
        <FormGroup>
          <Label for="projectName">Project Name</Label>
          <Input type="text" name="projectName" id="projectName" value={projectName} onChange={handleProjectNameChange} />
        </FormGroup>
        <FormGroup>
          <Label for="issueType">Issue Type</Label>
          <Input type="text" name="issueType" id="issueType" value={issueType} onChange={handleIssueTypeChange} />
        </FormGroup>
        <FormGroup>
          <Label for="workflow">Workflow</Label>
          <Input type="select" name="workflow" id="workflow" value={workflow} onChange={handleWorkflowChange}>
            <option value="">Select Workflow</option>
            <option value="to do">To Do</option>
            <option value="in progress">In Progress</option>
            <option value="done">Done</option>
            <option value="in review">In Review</option>
          </Input>
        </FormGroup>
        <Button color="primary" type="submit">
          Create
        </Button>
      </Form>
    </div>
  );
};

export default CreateProjectPage;
