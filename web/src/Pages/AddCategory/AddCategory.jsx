import React from 'react';
import './AddCategory.css';
import { Container, Row, Col, Button, Form } from 'react-bootstrap';

const AddCategory = () => {
  return (
    <Container className="d-flex justify-content-center align-items-center" style={{ minHeight: '100vh' }}>
      <Row className="w-100">
        <Col xs={12} md={6}>
          <div className="leftdiv">
            <Form>
              <fieldset>
                <Form.Group className="mb-3">
                  <Form.Label htmlFor="categoryImage">Category Image</Form.Label>
                  <Form.Control
                    type="file"
                    id="categoryImage"
                    accept="image/jpeg"
                  />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label htmlFor="categoryName">Category Name</Form.Label>
                  <Form.Control id="categoryName" placeholder="Add category Name" />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label htmlFor="status">Select Status</Form.Label>
                  <Form.Select id="status">
                    <option>---select status---</option>
                    <option>Active</option>
                    <option>Inactive</option>
                  </Form.Select>
                </Form.Group>
                <Button type="submit">Submit</Button>
              </fieldset>
            </Form>
          </div>
        </Col>
        <Col xs={12} md={6}>
          <div className="rightdiv">
            {/* Add your content here */}
            <h2>Right Side Content</h2>
            <p>This is where you can add additional content.</p>
          </div>
        </Col>
      </Row>
    </Container>
  );
}

export default AddCategory;
