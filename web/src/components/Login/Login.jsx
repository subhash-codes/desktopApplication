import React, { useState } from 'react';
import { Col, Form, Row, Button, Container, InputGroup } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { FaEye, FaEyeSlash, FaUser } from 'react-icons/fa';
import './Login.css';
import axios from 'axios';

const Login = ({ setIsLoggedIn, navigate }) => {
  const [data, setData] = useState({
    username: '',
    password: ''
  });
  const [showPassword, setShowPassword] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setData(prevData => ({ ...prevData, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const { username, password } = data;

    axios.post('http://localhost:3000/login', { username, password })
      .then(result => {
        if (result.data.success) {
          localStorage.setItem('isLoggedIn', 'true');
          localStorage.setItem('username', username);
          setIsLoggedIn(username);
          navigate('/pos');
          console.log(username);
          console.log(password);
          console.log(data);
        } else {
          alert(result.data.message);
        }
      })
      .catch(err => {
        console.error(err);
        alert("An error occurred. Please try again.");
      });
  };

  return (
    <div className="loginbody">
      <Container className="container-bg d-flex justify-content-center align-items-center" style={{ minHeight: '100vh' }}>
        <Row className="w-75">
          <Col xs={12} md={6} lg={4} className="mx-auto">
            <Form onSubmit={handleSubmit} className="p-4 shadow-sm rounded" style={{ background: '#fff' }}>
              <h3 className="text-center mb-4"><FaUser /> Login</h3>
              <hr />
              <Form.Group controlId="formEmail" className="mb-3">
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type="text"
                  name="username"
                  placeholder="Enter your username"
                  onChange={handleChange}
                  value={data.username}
                  required
                />
              </Form.Group>
              <Form.Group controlId="formPassword" className="mb-5">
                <Form.Label className="d-flex justify-content-between align-items-center">
                  <span>Password</span>
                  <a href="#" className="forgot-password-link">Forgot Password?</a>
                </Form.Label>
                <InputGroup>
                  <Form.Control
                    type={showPassword ? 'text' : 'password'}
                    name="password"
                    placeholder="Enter your password"
                    onChange={handleChange}
                    value={data.password}
                    required
                  />
                  <InputGroup.Text onClick={() => setShowPassword(!showPassword)}>
                    {showPassword ? <FaEyeSlash /> : <FaEye />}
                  </InputGroup.Text>
                </InputGroup>
              </Form.Group>
              <Button variant="primary" type="submit" className="w-100">
                Login
              </Button>
            </Form>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default Login;
