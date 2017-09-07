import React from 'react';
/**
 * @type {{ fontStyle, marginStyle }}
 */
import style from './style.css';


class Echo extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: props.test};
        //this.inputChange = this.inputChange.bind(this);
    }

    inputChange(event) {
        this.setState({
            value: event.target.value//document.getElementById('in').value
        });
    }

    render() {
        const {value} = this.state;
        // const fontStyle = {
        //     margin: '30px',
        //     fontSize: '20px'
        // };
        //console.log(fontStyle);
        return (
            <div>
                <p>{value}</p>
                <input className={[style.fontStyle, style.marginStyle].join(' ')} type="text" value={value} onChange={({target: {value}}) => this.setState({value})} />
            </div>
        );
    }

}

export default Echo;
