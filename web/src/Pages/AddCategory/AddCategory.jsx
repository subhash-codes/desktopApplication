import React, { useState, useEffect, useCallback } from 'react';
import './AddCategory.css';
import { Container, Row, Col, Button, Form } from 'react-bootstrap';
import { toast } from 'react-toastify';
import axios from 'axios';
import Modal from 'react-modal';

const AddCategory = ({ url, categoryid }) => {
  const [data, setData] = useState({ categoryname: '', category_image: '' });
  const [file, setFile] = useState(null);
  const [list, setList] = useState([]);
  const [editingCategory, setEditingCategory] = useState(null);

  const onChangeHandler = useCallback((event) => {
    const { value } = event.target;
    setData((data) => ({ ...data, categoryname: value }));
  }, []);

  const onFileChange = useCallback((event) => {
    setFile(event.target.files[0]);
  }, []);

  const fetchList = useCallback(async () => {
    try {
      const response = await axios.get(`${url}/categorymaster/getAll`);
      if (response.status === 200) {
        setList(response.data);
      } else {
        toast.error("Error fetching category list");
      }
    } catch (error) {
      console.error("Error fetching category list:", error);
      toast.error("Error fetching category list");
    }
  }, [url]);

  const removeCategory = useCallback(async (id) => {
    try {
      const response = await axios.delete(`${url}/categorymaster/${id}`);
      if (response.status === 204) {
        toast.success("Category deleted successfully");
        fetchList();
      } else {
        toast.error("Error deleting category");
      }
    } catch (error) {
      console.error("Error deleting category:", error);
      toast.error("Error deleting category");
    }
  }, [url, fetchList]);

  const editCategory = (category) => {
    setEditingCategory(category);
  };

  const categoryId = Number(categoryid);
  // @@@@@@@@@@@@@@@@@@ FETCHING CATEGORY USING ID TO ADD LOGIC FOR UPDATING CATEGORY @@@@@@@@@@@@@@@@@@@@@@@
  useEffect(() => {
    // Fetch the current category details
   axios.get(`http://localhost:3000/categorymaster/19`)
      .then(response => {
        setEditingCategory(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.error('There was an error fetching the category!', error);
      });
  }, [categoryid]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setEditingCategory({ ...editingCategory, [name]: value });
  };

  // const handleInputChange = (e) => {
  //   const { name, value } = e.target;
  //   setEditingCategory((prev) => ({ ...prev, [name]: value }));
  // };

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  // const handleFileChange = (e) => {
  //   setEditingCategory((prev) => ({
  //     ...prev,
  //     file: e.target.files[0],
  //   }));
  // };


  const handleEditSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('category', JSON.stringify(editingCategory));
    if (file) {
      formData.append('file', file);
    } else {
      formData.append('file', new Blob(), '');
    }

    try {
      const response = await axios.put(`http://localhost:3000/categorymaster/19`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Accept': 'application/json'
        },
      });
      if (response.status === 200) {
        console.log('Category updated successfully!', response.data);

        toast.success('Category updated successfully');
        setEditingCategory(null);
        fetchList();
      } else {
        console.error('There was an error updating the category!', error);
        toast.error('Error updating category');
      }
    } catch (error) {
      console.error('Error updating category:', error);
      toast.error('Error updating category');
    }
  };




  const onSubmitHandler = useCallback(async (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append('category', JSON.stringify(data));
    if (file) {
      formData.append('file', file);
    }

    try {
      const response = await axios.post(`${url}/categorymaster/addcategory`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          'Accept': 'application/json'
        },
      });
      if (response.status === 200) {
        toast.success('Category added successfully');
        setData({ categoryname: '' });
        setFile(null);
        fetchList();
      } else {
        toast.error('Error adding category');
      }
    } catch (error) {
      console.error('Error:', error);
      toast.error('Error adding category');
    }
  }, [data, file, fetchList, url]);



 



  useEffect(() => {
    fetchList();
  }, [fetchList]);

  return (
    <Container className="d-flex justify-content-center align-items-center" style={{ minHeight: '80vh' }}>
      <Row className="w-100">
        <h4>Add Category</h4>
        <Col xs={12} md={6}>
          <div className="leftdiv">
            <Form className='form' onSubmit={onSubmitHandler}>
              <fieldset>
                <Form.Group className="mb-3">
                  <Form.Label htmlFor="categoryImage">Category Image</Form.Label>
                  <Form.Control
                    type="file"
                    name='file'
                    id="categoryImage"
                    onChange={onFileChange}
                    required
                  />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label htmlFor="categoryName">Category Name</Form.Label>
                  <Form.Control
                    id="categoryName"
                    onChange={onChangeHandler}
                    value={data.categoryname}
                    type="text"
                    name='categoryname'
                    placeholder='Add category name'
                    required
                  />
                </Form.Group>
                <Button type="submit">Add Category</Button>
              </fieldset>
            </Form>
          </div>
        </Col>

        {/* List of Categories */}
        <Col xs={12} md={6}>
          <div className="rightdiv">
            <div className="category-list flex-col">
              <div className="list-category-nav">
                <p>All Category List</p>
              </div>
              <div className="category-list-table">
                <div className="category-list-table-format title">
                  <b>Category Image</b>
                  <b>Category Name</b>
                  <b>Action</b>
                </div>

                {list.map((item, index) => (
                  <div key={index} className="category-list-table-format">
                    <img src={`${url}/images/${item.category_image}`} alt={item.categoryname} style={{ width: '50px', height: '50px' }} />
                    <p>{item.categoryname}</p>
                    <div className="action-btn">
                      <p onClick={() => removeCategory(item.categoryid)} className='dlt-btn'>Delete</p>
                      <p onClick={() => editCategory(item)} className='edit-btn'>Edit</p>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </Col>

        {/* Modal for Editing Category */}
        <Modal
          isOpen={!!editingCategory}
          onRequestClose={() => setEditingCategory(null)}
          contentLabel="Edit Category"
          className="Modal"
          overlayClassName="Overlay"
        >
          {editingCategory && (
            <div className="edit-form">
              <h2>Update Category</h2>
              <p onClick={() => setEditingCategory(null)} className="cross">X</p>
              <Form className='form' onSubmit={handleEditSubmit}>
                <fieldset>
                  <Form.Group className="mb-3">
                    <Form.Label htmlFor="editCategoryImage">Category Image</Form.Label>
                    <Form.Control
                      type="file"
                      name='file'
                      id="editCategoryImage"
                      onChange={handleFileChange}
                    />
                  </Form.Group>
                  <Form.Group className="mb-3">
                    <Form.Label htmlFor="editCategoryName">Category Name</Form.Label>
                    <Form.Control
                      id="editCategoryName"
                      onChange={handleInputChange}
                      value={editingCategory.categoryname}
                      type="text"
                      name='categoryname'
                      placeholder='Edit category name'
                      required
                    />
                  </Form.Group>
                  <Button type="submit">Update Category</Button>
                </fieldset>
              </Form>
            </div>
          )}
        </Modal>
      </Row>
    </Container>
  );
};

export default AddCategory;
