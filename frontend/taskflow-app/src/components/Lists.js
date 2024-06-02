import React, { useState, useEffect } from "react";         
import {TextField, Card} from '@mui/material/';
import { styled } from '@mui/system';

const CardPendente = styled(Card)({
    backgroundColor: "#002364",
    borderRadius: '20px'
});

export default function ReturnLists({ lists, updateListTitle }) { 
    
    //console.log(lists);
    const [tempTitles, setTempTitles] = useState({}); 

    const handleChange = (index, newTitle) => {
        setTempTitles(prev => ({ ...prev, [index]: newTitle }));
    };

    const handleBlur = (index) => {
        if (tempTitles[index] !== undefined && tempTitles[index] !== lists[index].title) {
            updateListTitle(index, tempTitles[index]);
        }
    };

    const handleKeyDown = (index, e) => {
        if (e.key === "Enter") {
            e.preventDefault(); 
            handleBlur(index);
        }
    };

    return (
        <div>            
            <div className="row">
                {lists.map((list, index) => (
                    <CardPendente key={index} id={list.id} className="col-md-3 mx-auto my-1">
                        <TextField  
                            variant="standard"
                            fullWidth
                            value={tempTitles[index] !== undefined ? tempTitles[index] : list.title}
                            onChange={(e) => handleChange(index, e.target.value)}
                            onBlur={() => handleBlur(index)}
                            onKeyDown={(e) => handleKeyDown(index, e)}
                            InputProps={{
                                style: { color: '#fff' }
                            }}
                        />         
                    </CardPendente>
                ))}
            </div>
        </div>
    );    
}