import React,{useState, useEffect} from 'react'
import './AddProduct.css'
import { Container, Row, Col, Button, Form } from 'react-bootstrap';
import { toast } from 'react-toastify';
import axios from 'axios';

const AddProduct = ({url}) => {

  // const url = "http://localhost:4000";

  const [image, setImage] = useState(false);
  const [categories, setCategories] = useState([]);

  const [data, setData] = useState({
    name: "",
    description: "",
    price: "",
    category: "Pizza"
  })

  // @@@@@@@@@@@@@@@@@@@@ FETCH CATEGORY LOGIC @@@@@@@@@@@@@@@@@@@@@@@@@@/
  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get(`${url}/api/category/list`);
        if (response.data.success) {
          setCategories(response.data.data);
        } else {
          toast.error("Error fetching categories");
        }
      } catch (error) {
        console.error("Error fetching categories:", error);
        toast.error("Error fetching categories");
      }
    };
    fetchCategories();
  }, []);



  const onChangeHandler = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setData(data => ({ ...data, [name]: value }))
  }

  const onSubmitHandler = async (event) => {

    event.preventDefault();
    const formData = new FormData();
    formData.append("name", data.name)
    formData.append("description", data.description)
    formData.append("price", Number(data.price))
    formData.append("category", data.category)
    formData.append("image", image)
    const response = await axios.post(`${url}/api/food/add`, formData)

    if (response.data.success) {
      setData({
        name: "",
        description: "",
        price: "",
        category: "Salad"
      })
      setImage(false)
      toast.success(response.data.message)
      

    } else {
      toast.error(response.data.message)

    }
  }
  const handleNavigateButton = (e) => {
    navigate("/listProduct");
  }

  // useEffect(()=>{
  //   console.log(data);
  // },[data])

  return (
    <Container className="d-flex justify-content-center align-items-center" style={{ minHeight: '80vh',  }}>
      <Row className="w-100">
        <h4>Add Products</h4>
        <Col xs={12} md={6}>
          <div className="leftdiv">
            <Form onSubmit={onSubmitHandler}>
              <fieldset>
                <Form.Group className="mb-3">
                  <Form.Label htmlFor="productImage">Product Image</Form.Label>
                  <Form.Control
                    type="file"
                    id="categoryImage"
                    accept="image/jpeg"
                  />
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label htmlFor="productName">Product Name</Form.Label>
                  <Form.Control id="categoryName" onChange={onChangeHandler} value={data.name} type="text " name='name' placeholder='Type here' />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label htmlFor="status">Select Category</Form.Label>
                  <Form.Select id="categoryName" onChange={onChangeHandler} value={data.category} name="category">
                    <option value=""> Select Category </option>
                    {categories.map(category => (
                      <option key={category._id} value={category.name}>{category.name}</option>
                    ))}
                  </Form.Select>
                </Form.Group>
                <Form.Group className="mb-3">
                  <Form.Label htmlFor="categoryName">Product Price</Form.Label>
                  <Form.Control id="productPrice" onChange={onChangeHandler} value={data.price} type="Number" name='price' placeholder='$20' />
                </Form.Group>
                <Button type="submit">Add Products</Button>
              </fieldset>
            </Form>
          </div>
        </Col>
        <Col xs={12} md={6}>

        {/* @@@@@@@@@@@@@@@@@@@@@@ Right Div @@@@@@@@@@@@@@@@@@@@@@@@@@@@@ */}

          <div className="rightdiv">
            {/* Add your content here */}
            <h2>Right Side Content</h2>
            <p>This is where you can add additional content.</p>
          </div>
        </Col>
      </Row>
    </Container>
  )
}

export default AddProduct