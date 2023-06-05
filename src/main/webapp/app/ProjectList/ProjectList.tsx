// import React, { useState, useEffect } from 'react';
// import { Translate } from 'react-jhipster';
// import { Container, Row, Col, Card } from 'react-bootstrap';
//
// const ProjectsList = () => {
//   const [projects, setProjects] = useState([]);
//
//   useEffect(() => {
//     const fetchProjects = async () => {
//       try {
//         const accessToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTY4NTgxNjUwMH0.qTXapJsMYVvvSU3ZAKiG6URHdMgz9ng-YSz8_z9_DVF7UADYME66svrHZ1igK_uCjngjdIuty1JVVc_jZhVjKQ';
//
//         const response = await fetch('http://localhost:8080/api/project', {
//           headers: {
//             Authorization: `Bearer ${accessToken}`,
//           },
//         });
//
//         if (response.ok) {
//           const projectsData = await response.json();
//           setProjects(projectsData);
//         } else {
//           console.error('Failed to fetch projects:', response.status);
//         }
//       } catch (error) {
//         console.error('An error occurred:', error);
//       }
//     };
//
//     fetchProjects();
//   }, []);
//
//   return (
//     <Container>
//       <h3>
//         <Translate contentKey="home.recentProjects">Recent Projects</Translate>
//       </h3>
//       <Row>
//         {projects.map((project) => (
//           <Col md="4" key={project.id}>
//             <Card className="mb-4">
//               <Card.Body>
//                 <Card.Title>{project.name}</Card.Title>
//                 <Card.Text>{project.description}</Card.Text>
//
//               </Card.Body>
//             </Card>
//           </Col>
//         ))}
//       </Row>
//     </Container>
//   );
// };
//
// export default ProjectsList;
