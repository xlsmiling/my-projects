import React from 'react';

class Hello extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: props.value};
        this.clickReverse = this.clickReverse.bind(this);
    }

    clickReverse() {
        this.setState({
            value: this.state.value.split('').reverse().join(''),
        });
    }

    render() {
        const {value} = this.state;
        return (
            <div>
                {value}
                <button onClick={this.clickReverse}>reverse</button>
            </div>
        );
    }

}

export default Hello;
