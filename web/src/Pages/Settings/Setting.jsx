import React, { useState, useEffect } from 'react';
import './Setting.css';
import { Container, Row, Col, Form, Button } from 'react-bootstrap';
import axios from 'axios';
import { FaShoppingBag, FaUser, FaMapPin, FaHeart, FaTag } from 'react-icons/fa';

const Setting = () => {
    const [businessDetails, setBusinessDetails] = useState({
        businessName: "",
        businessMobile: "",
        businessEmail: "",
        businessAddress: "",
        businessGstNumber: "",
        businessLogo: null,
    });
    const [existingPassword, setExistingPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");

    useEffect(() => {
        // Fetch business details
        const fetchBusinessDetails = async () => {
            try {
                const response = await axios.get('/api/business');
                setBusinessDetails(response.data);
            } catch (error) {
                console.error('Error fetching business details:', error);
            }
        };

        fetchBusinessDetails();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setBusinessDetails(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleFileChange = (e) => {
        setBusinessDetails(prevState => ({
            ...prevState,
            businessLogo: e.target.files[0]
        }));
    };

    const handleExistingPasswordChange = (e) => {
        setExistingPassword(e.target.value);
    };

    const handleNewPasswordChange = (e) => {
        setNewPassword(e.target.value);
    };

    const handleSubmitPassword = async (e) => {
        e.preventDefault();

        if (newPassword.length === 0) {
            alert('Please enter a new password.');
            return;
        }

        // Validate existing password
        try {
            const response = await axios.post('/api/business/validate-password', { existingPassword });

            if (response.data.valid) {
                // Proceed to update details including the new password
                const formData = new FormData();
                Object.keys(businessDetails).forEach(key => {
                    formData.append(key, businessDetails[key]);
                });
                formData.append('newPassword', newPassword);

                try {
                    await axios.post('/api/business/update', formData);
                    alert('Business details updated successfully');
                } catch (error) {
                    console.error('Error updating business details:', error);
                }
            } else {
                alert('Existing password is incorrect.');
            }
        } catch (error) {
            console.error('Error validating existing password:', error);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        Object.keys(businessDetails).forEach(key => {
            formData.append(key, businessDetails[key]);
        });
        formData.append('password', password);

        try {
            await axios.post('/api/business/update', formData);
            alert('Business details updated successfully');
        } catch (error) {
            console.error('Error updating business details:', error);
        }
    };

    return (
        <Container className="mt-5 ">
            <Row className='d-flex justify-content-center'>
                <Col lg={3} className="pb-5 bg-white rounded-3 p-4 me-lg-3">
                            <h4>Update Password</h4>
                            <form onSubmit={handleSubmitPassword}>
                            <Form.Group controlId="existingPassword">
                                <Form.Label>Existing Password</Form.Label>
                                <Form.Control
                                    type="password"
                                    value={existingPassword}
                                    onChange={handleExistingPasswordChange}
                                    placeholder="Enter existing password"
                                />
                            </Form.Group>

                            <Form.Group controlId="newPassword">
                                <Form.Label>New Password</Form.Label>
                                <Form.Control
                                    type="password"
                                    value={newPassword}
                                    onChange={handleNewPasswordChange}
                                    placeholder="Enter new password"
                                />
                            </Form.Group>
                            <Form.Group controlId="newPassword">
                                <Button type='submit' className='mt-3' >Change Password</Button>
                            </Form.Group>

                            </form>
                </Col>
            
                <Col lg={8} className="pb-5 bg-white rounded-3 p-4">
                <h4>Update settings</h4>
                    <Form className="row" onSubmit={handleSubmit}>
                        <Col md={6}>
                            <Form.Group controlId="businessName">
                                <Form.Label>Business Name</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="businessName"
                                    value={businessDetails.businessName}
                                    onChange={handleChange}
                                    placeholder="Enter business name"
                                />
                            </Form.Group>
                        </Col>
                        <Col md={6}>
                            <Form.Group controlId="businessMobile">
                                <Form.Label>Business Mobile</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="businessMobile"
                                    value={businessDetails.businessMobile}
                                    onChange={handleChange}
                                    placeholder="Enter business mobile"
                                />
                            </Form.Group>
                        </Col>
                        <Col md={6}>
                            <Form.Group controlId="businessEmail">
                                <Form.Label>Business Email</Form.Label>
                                <Form.Control
                                    type="email"
                                    name="businessEmail"
                                    value={businessDetails.businessEmail}
                                    onChange={handleChange}
                                    placeholder="Enter business email"
                                />
                            </Form.Group>
                        </Col>
                        <Col md={6}>
                            <Form.Group controlId="businessAddress">
                                <Form.Label>Business Address</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="businessAddress"
                                    value={businessDetails.businessAddress}
                                    onChange={handleChange}
                                    placeholder="Enter business address"
                                />
                            </Form.Group>
                        </Col>
                        <Col md={6}>
                            <Form.Group controlId="businessGstNumber">
                                <Form.Label>Business GST Number</Form.Label>
                                <Form.Control
                                    type="text"
                                    name="businessGstNumber"
                                    value={businessDetails.businessGstNumber}
                                    onChange={handleChange}
                                    placeholder="Enter business GST number"
                                />
                            </Form.Group>
                        </Col>
                        <Col md={6}>
                            <Form.Group controlId="businessLogo">
                                <Form.Label>Business Logo</Form.Label>
                                <Form.Control
                                    type="file"
                                    name="businessLogo"
                                    onChange={handleFileChange}
                                    accept="image/*"
                                />
                            </Form.Group>
                        </Col>
                        
                        <Col className="col-12">
                            <hr className="mt-2 mb-3" />
                            <div className="d-flex flex-wrap justify-content-between align-items-center">
                                <Button variant="primary" type="submit">
                                    Update Details
                                </Button>
                            </div>
                        </Col>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default Setting;
