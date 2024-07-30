import React from 'react';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { NavLink } from 'react-router-dom';
import { FaUser, FaCog, FaSignOutAlt } from 'react-icons/fa';

const NavbarHeader = ({ onLogout }) => {
  return (
    <Navbar bg="dark" data-bs-theme="dark" className="text-white">
      <Container>
        <Navbar.Brand href="#home">Billing App</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav" className="d-flex justify-content-between">
          <Nav className="me-auto">
            <NavLink to='/dashboard' className="nav-link">
              Dashbord
            </NavLink>
            <NavLink to='/pos' className="nav-link">
              POS
            </NavLink>
            <NavLink to='/addCategory' className="nav-link">
              Add Category
            </NavLink>
            <NavLink to='/addProduct' className="nav-link">
              Add Product
            </NavLink>
            {/* <Nav.Link href="#link">Link</Nav.Link>
            <NavDropdown title="Dropdown" id="basic-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">
                Another action
              </NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">
                Separated link
              </NavDropdown.Item>
            </NavDropdown> */}
          </Nav>
          <Nav>
            <NavDropdown 
              title={<FaUser />} 
              id="user-dropdown" 
              align="end"
              className="user-dropdown"
            >
              <NavDropdown.Item >
                <NavLink to='/setting' className='nav-link' style={{color:'white'}}>
                <FaCog /> Settings
                </NavLink>
              </NavDropdown.Item>
              <NavDropdown.Item onClick={onLogout} >
                <NavLink className='nav-link'>
                <FaSignOutAlt /> Logout
                </NavLink>
              </NavDropdown.Item>
            </NavDropdown>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavbarHeader;
