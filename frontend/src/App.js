import React, { useState } from 'react';
import axios from 'axios';
import Select from 'react-select';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

const transformOptions = [
  { value: 'UPPER', label: 'UPPER' },
  { value: 'LOWER', label: 'LOWER' },
  { value: 'CAPITALIZE', label: 'CAPITALIZE' },
  { value: 'ABBREVIATE', label: 'ABBREVIATE' },
  { value: 'EXPAND', label: 'EXPAND' },
  { value: 'INVERSE', label: 'INVERSE' },
  { value: 'NUM_EXPAND', label: 'NUM_EXPAND' },
  { value: 'NOREPEAT', label: 'NOREPEAT'}
];

function App() {
  const [inputText, setInputText] = useState('');
  const [selectedTransforms, setSelectedTransforms] = useState([transformOptions[0]]); // Default transform
  const [transformedText, setTransformedText] = useState('');

  const handleTransform = async () => {
    const transforms =
        selectedTransforms.map((transform) => transform.value).join(',');

    try {
      const response = await axios.get(`http://localhost:8080/${inputText}?transforms=${transforms}`);

      // Assuming the response contains transformed text in JSON format
      setTransformedText(response.data.transformedText); // Update the state with transformed text
    } catch (error) {
      console.error('Error fetching transformed text:', error);
    }
  };

  return (
      <div className="container mt-4">
        <h1>Text Transformer</h1>
        <div className="input-group mb-3">
          <input
              type="text"
              className="form-control"
              value={inputText}
              onChange={(e) => setInputText(e.target.value)}
              placeholder="Enter text"
          />
          <div className="input-group-append">
            <button className="btn btn-primary" type="button" onClick={handleTransform}>
              Transform
            </button>
          </div>
        </div>
        <Select
            options={transformOptions}
            value={selectedTransforms}
            onChange={setSelectedTransforms}
            isMulti
            placeholder="Select transforms..."
        />
        {transformedText && (
            <div className="mt-4">
              <h2>Transformed Text</h2>
              <p>{transformedText}</p>
            </div>
        )}
      </div>
  );
}

export default App;
