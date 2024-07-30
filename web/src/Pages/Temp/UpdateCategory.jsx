// src/components/UpdateCategory.js
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const UpdateCategory = ({ categoryId }) => {
    const [category, setCategory] = useState({
        categoryname: '',
        category_image: '',
    });
    const [file, setFile] = useState(null);

    useEffect(() => {
        // Fetch the current category details
        axios.get(`http://localhost:3000/categorymaster/${categoryId}`)
            .then(response => {
                setCategory(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the category!', error);
            });
    }, [categoryId]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setCategory({ ...category, [name]: value });
    };

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('category', JSON.stringify(category));
        if (file) {
            formData.append('file', file);
        }

        axios.put(`http://localhost:3000/categorymaster/${categoryId}`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        })
            .then(response => {
                console.log('Category updated successfully!', response.data);
            })
            .catch(error => {
                console.error('There was an error updating the category!', error);
            });
    };

    return (
        <div>
            <h2>Update Category</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Category Name:</label>
                    <input
                        type="text"
                        name="categoryname"
                        value={category.categoryname}
                        onChange={handleChange}
                    />
                </div>
                <div>
                    <label>Category Image:</label>
                    <input
                        type="file"
                        onChange={handleFileChange}
                    />
                </div>
                <button type="submit">Update Category</button>
            </form>
        </div>
    );
};

export default UpdateCategory;
