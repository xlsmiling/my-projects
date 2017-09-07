import React from 'react';

class ClickCounters extends React.Component {
    constructor(props) {
        super(props);
        this.state = {count: props.value};
        this.clickEvent = this.clickEvent.bind(this);
    }

    clickEvent(){
        this.setState ({count: this.state.count + 1});
    }

    render() {
        const {count} = this.state;
        return (
            <div>
                <p>{count}</p>
                <button onClick={this.clickEvent}>click</button>
            </div>
        )
    }
}

export default ClickCounters;